package com.example.backend.Controller;

import com.example.backend.DTO.RoomDTO;
import com.example.backend.DTO.TeacherDTO;
import com.example.backend.Entity.Room;
import com.example.backend.Repository.RoomRepository;
import com.example.backend.Services.RoomService.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public HttpEntity<?> addRoom(@RequestBody RoomDTO room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public HttpEntity<?> getAllRooms(){
        return roomService.getAllRooms();
    }

    @PutMapping({"/{id}"})
    public HttpEntity<?>editRoom(@PathVariable UUID id, @RequestBody RoomDTO room){
        return roomService.editRoom(id, room);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable UUID id) {
        return roomService.deleteRoomById(id);
    };
}
