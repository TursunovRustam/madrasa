package com.example.backend.Services.RoomService;

import com.example.backend.DTO.RoomDTO;
import com.example.backend.DTO.TeacherDTO;
import com.example.backend.Entity.Room;
import com.example.backend.Entity.Teacher;
import com.example.backend.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public HttpEntity<?> addRoom(RoomDTO room) {
        Room save = roomRepository.save(new Room(room.getName(), LocalDateTime.now()));
        return ResponseEntity.ok(save);
    }

    @Override
    public HttpEntity<?> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAllByOrderByTime());
    }

    @Override
    public HttpEntity<?> editRoom(UUID roomId, RoomDTO room) {
        LocalDateTime time = roomRepository.findById(roomId).orElseThrow().getTime();
        roomRepository.save(new Room(roomId, room.getName(), time));
        return ResponseEntity.ok(room);
    }

    @Override
    public HttpEntity<?> deleteRoomById(UUID id) {
       roomRepository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully!");
    }



}
