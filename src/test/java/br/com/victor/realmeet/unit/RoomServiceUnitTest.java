package br.com.victor.realmeet.unit;

import br.com.victor.realmeet.core.BaseUnitTest;
import br.com.victor.realmeet.domain.entity.Room;
import br.com.victor.realmeet.domain.repository.RoomRepository;
import br.com.victor.realmeet.dto.response.RoomResponse;
import br.com.victor.realmeet.exception.RoomNotFoundException;
import br.com.victor.realmeet.service.RoomService;
import br.com.victor.realmeet.utils.MapperUtils;
import br.com.victor.realmeet.utils.TestConstants;
import br.com.victor.realmeet.utils.TestDataCreator;
import br.com.victor.realmeet.validator.RoomValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoomServiceUnitTest extends BaseUnitTest {

    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomValidator roomValidator;

    @BeforeEach
    public void setupEach() {
        roomService = new RoomService(roomRepository, MapperUtils.newRoomMapper(), roomValidator);
    }

    @Test
    void testGetRoom() {
        Room room = TestDataCreator.newRoomBuilder().id(TestConstants.DEFAULT_ROOM_ID).build();
        when(roomRepository.findByIdAndActive(TestConstants.DEFAULT_ROOM_ID, true)).thenReturn(Optional.of(room));

        RoomResponse roomResponse = roomService.getRoom(TestConstants.DEFAULT_ROOM_ID);

        assertEquals(room.getName(), roomResponse.getName());
        assertEquals(room.getSeats(), roomResponse.getSeats());

    }


    @Test
    void testGetRoomNotFound() {
        when(roomRepository.findByIdAndActive(TestConstants.DEFAULT_ROOM_ID, true)).thenReturn(Optional.empty());
        assertThrows(RoomNotFoundException.class, () -> roomService.getRoom(TestConstants.DEFAULT_ROOM_ID));
    }
}