package br.com.victor.realmeet.utils;

import br.com.victor.realmeet.mapper.AllocationMapper;
import br.com.victor.realmeet.mapper.RoomMapper;
import org.mapstruct.factory.Mappers;

public final class MapperUtils {

    private MapperUtils() {
    }

    public static RoomMapper newRoomMapper() {
        return Mappers.getMapper(RoomMapper.class);
    }

    public static AllocationMapper newAllocationMapper() {
        return Mappers.getMapper(AllocationMapper.class);
    }
}
