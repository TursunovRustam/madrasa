package com.example.backend.Controller;

import com.example.backend.DTO.LessonDTO;
import com.example.backend.DTO.RoomDTO;
import com.example.backend.Services.LessonService.LessonService;
import com.example.backend.Services.RoomService.RoomService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/{groupId}")
    public HttpEntity<?> getLesson(@PathVariable UUID groupId){
        return ResponseEntity.ok(lessonService.findByGroupId(groupId));
    }
    @PutMapping("/{groupId}")
    public HttpEntity<?> editLesson(@PathVariable UUID groupId,@RequestBody LessonDTO lessonDTO) {
        return lessonService.editLessonOfGroup(groupId,lessonDTO);
    }

    @PostMapping("/{currentGroup}")
    private HttpEntity<?> addLesson(@RequestBody LessonDTO lessonDTO, @PathVariable UUID currentGroup){
        System.out.println("HEllo");
        return lessonService.addLesson(lessonDTO, currentGroup);
    }


}
