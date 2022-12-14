package br.com.victor.realmeet.validator;

import br.com.victor.realmeet.dto.request.RoomRequest;
import br.com.victor.realmeet.dto.request.UpdateRoomRequest;
import org.springframework.stereotype.Component;

import static br.com.victor.realmeet.validator.ValidatorConstants.*;
import static br.com.victor.realmeet.validator.ValidatorUltils.*;

@Component
public class RoomValidator {


    public void validate(RoomRequest roomRequest) {

        ValidationErros validationErrors = new ValidationErros();

        validateRequired(roomRequest.getName(), ROOM_NAME, validationErrors);
        validateMaxLength(roomRequest.getName(), ROOM_NAME, ROOM_NAME_MAX_LENGTH, validationErrors);

        validateRequired(roomRequest.getSeats(), ROOM_SEATS, validationErrors);
        validateMinValue(roomRequest.getSeats(),ROOM_SEATS, ROOM_SEATS_MIN_VALUE, validationErrors);
        validateMaxValue(roomRequest.getSeats(),ROOM_SEATS,ROOM_SEATS_MAX_VALUE,validationErrors);

        ValidatorUltils.throwOnError(validationErrors);

    }

    public void validate(UpdateRoomRequest updateRoomRequest) {

        ValidationErros validationErrors = new ValidationErros();

        validateRequired(updateRoomRequest.getName(), ROOM_NAME, validationErrors);
        validateMaxLength(updateRoomRequest.getName(), ROOM_NAME, ROOM_NAME_MAX_LENGTH, validationErrors);

        validateRequired(updateRoomRequest.getSeats(), ROOM_SEATS, validationErrors);
        validateMinValue(updateRoomRequest.getSeats(),ROOM_SEATS, ROOM_SEATS_MIN_VALUE, validationErrors);
        validateMaxValue(updateRoomRequest.getSeats(),ROOM_SEATS,ROOM_SEATS_MAX_VALUE,validationErrors);

        ValidatorUltils.throwOnError(validationErrors);

    }
}
