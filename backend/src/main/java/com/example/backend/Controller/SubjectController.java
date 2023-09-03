package com.example.backend.Controller;

import com.example.backend.DTO.RoomDTO;
import com.example.backend.DTO.SubjectDTO;
import com.example.backend.DTO.TeacherDTO;
import com.example.backend.Repository.SubjectRepository;
import com.example.backend.Services.SubjectService.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @PostMapping
    public HttpEntity<?> addSubject(@RequestBody SubjectDTO subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping
    public HttpEntity<?> getAllSubject(){
        return subjectService.getAllSubject();
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editSubject(@PathVariable UUID id, @RequestBody SubjectDTO dto) {
        return subjectService.editSubject(id,dto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable UUID id) {
        return subjectService.deleteSubjectById(id);
    };

}
