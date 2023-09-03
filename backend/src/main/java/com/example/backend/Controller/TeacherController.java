package com.example.backend.Controller;

import com.example.backend.DTO.GroupDTO;
import com.example.backend.DTO.RoomDTO;
import com.example.backend.DTO.TeacherDTO;
import com.example.backend.Services.TeacherService.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @PostMapping
    public HttpEntity<?> addTeacher(@RequestBody TeacherDTO teacher) {
        return teacherService.addTeacher(teacher);
    }

    @GetMapping
    public HttpEntity<?> getAllTeachers(){
        return teacherService.getAllTeachers();
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editTeacher(@PathVariable UUID id, @RequestBody TeacherDTO dto) {
        return teacherService.editTeacher(id,dto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable UUID id) {
        return teacherService.deleteTeacherById(id);
    };


}
