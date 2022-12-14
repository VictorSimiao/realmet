package br.com.victor.realmeet.controller;

import br.com.victor.realmeet.dto.request.RoomRequest;
import br.com.victor.realmeet.dto.request.UpdateRoomRequest;
import br.com.victor.realmeet.dto.response.RoomResponse;
import br.com.victor.realmeet.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "rooms", description = "Endpoints para gerenciamento de salas")
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "Buscar sala",description = "Recurso para buscar uma sala pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno OK com a busca da sala"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "403", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos fornecidos")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable(value = "id") Long id, @RequestHeader(value = "api-key", required = true) String apiKey) {
        return ResponseEntity.ok(roomService.getRoom(id));
    }

    @Operation(summary = "Criar sala",description = "Recurso para criar uma sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest roomRequest, @RequestHeader(value = "api-key", required = true) String apiKey) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(roomRequest));
    }

    @Operation(summary = "Deletar sala",description = "Recurso para deletar uma sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sala deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "403", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos fornecidos")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable(value = "id") Long id, @RequestHeader(value = "api-key", required = true) String apiKey) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Atualizar sala",description = "Recurso para atualizar uma sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sala atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "403", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos fornecidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody UpdateRoomRequest updateRoomRequest, @RequestHeader(value = "api-key", required = true) String apiKey) {
        roomService.updateRoom(id, updateRoomRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Listar salas ",description = "Recurso para listar salas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno OK"),
            @ApiResponse(responseCode = "403", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos fornecidos")
    })
    @GetMapping
    public ResponseEntity<List<RoomResponse>> listRooms(
            @RequestHeader(value = "api-key", required = true) String apiKey,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer page) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.listRooms(name, active, orderBy, limit, page));
    }
}
