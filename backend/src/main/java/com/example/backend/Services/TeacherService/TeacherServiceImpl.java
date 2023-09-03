package com.example.backend.Services.TeacherService;

import com.example.backend.DTO.GroupDTO;
import com.example.backend.DTO.TeacherDTO;
import com.example.backend.Entity.Group;
import com.example.backend.Entity.Subject;
import com.example.backend.Entity.Teacher;
import com.example.backend.Repository.SubjectRepository;
import com.example.backend.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public HttpEntity<?> addTeacher(TeacherDTO teacher) {
        Teacher save = teacherRepository.save(new Teacher(teacher.getName(), LocalDateTime.now()));
        return ResponseEntity.ok(save);
    }

    @Override
    public HttpEntity<?> getAllTeachers() {
        return ResponseEntity.ok(teacherRepository.findAllByOrderByTime());
    }

    @Override
    public HttpEntity<?> editTeacher(UUID id, TeacherDTO dto) {
        LocalDateTime time = teacherRepository.findById(id).orElseThrow().getTime();

        List<Subject> subjects = new ArrayList<>();


        teacherRepository.save(new Teacher(id, dto.getName(), time));
       return ResponseEntity.ok(dto);
    }

    @Override
    public HttpEntity<?> deleteTeacherById(UUID id) {
        teacherRepository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully!");
    }


}
