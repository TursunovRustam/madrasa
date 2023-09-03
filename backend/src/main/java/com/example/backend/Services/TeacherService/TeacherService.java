package com.example.backend.Services.TeacherService;

import com.example.backend.DTO.TeacherDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface TeacherService {

    HttpEntity<?> addTeacher(TeacherDTO teacher);

    HttpEntity<?> getAllTeachers();

    HttpEntity<?> editTeacher(UUID id, TeacherDTO dto);

    HttpEntity<?> deleteTeacherById(UUID id);
}
