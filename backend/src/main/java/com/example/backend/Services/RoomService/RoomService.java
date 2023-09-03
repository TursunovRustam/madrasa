package com.example.backend.Services.RoomService;

import com.example.backend.DTO.RoomDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface RoomService {

    HttpEntity<?> addRoom(RoomDTO room);

    HttpEntity<?> getAllRooms();

    HttpEntity<?> editRoom(UUID roomId, RoomDTO room);

    HttpEntity<?> deleteRoomById(UUID id);
}
