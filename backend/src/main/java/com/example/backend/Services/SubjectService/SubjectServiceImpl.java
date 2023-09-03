package com.example.backend.Services.SubjectService;

import com.example.backend.DTO.SubjectDTO;
import com.example.backend.Entity.Subject;
import com.example.backend.Entity.Teacher;
import com.example.backend.Repository.SubjectRepository;
import com.example.backend.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    @Override
    public HttpEntity<?> addSubject(SubjectDTO subject) {
        Subject savedSubject = subjectRepository.save(new Subject(subject.getName(),  getTeachers(subject), LocalDateTime.now()));
        return ResponseEntity.ok(savedSubject);
    }

    @Override
    public HttpEntity<?> getAllSubject() {
        List<Subject> all = subjectRepository.findAllByOrderByTime();
        return ResponseEntity.ok(all);
    }

    @Override
    public HttpEntity<?> editSubject(UUID id, SubjectDTO dto) {
        System.out.println(dto.getTeacherIds());
        Subject existingSubject = subjectRepository.findById(id).orElseThrow();

        LocalDateTime time = existingSubject.getTime();
        List<Teacher> teachers = getTeachers(dto);

        existingSubject.setName(dto.getName());
        existingSubject.setTeachers(teachers);

        subjectRepository.save(existingSubject);
        return ResponseEntity.ok(dto);
    }


    @Override
    public HttpEntity<?> deleteSubjectById(UUID id) {
        subjectRepository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully!");
    }


    private List<Teacher> getTeachers(SubjectDTO subject) {
        List<Teacher> teachers= new ArrayList<>();
        for (UUID teacherId : subject.getTeacherIds()) {
            teachers.add(teacherRepository.findById(teacherId).orElseThrow());
        }
        return teachers;
    }
}
