package com.example.backend.Services.SubjectService;

import com.example.backend.DTO.SubjectDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface SubjectService {

    HttpEntity<?> addSubject(SubjectDTO subject);

    HttpEntity<?> getAllSubject();

    HttpEntity<?> editSubject(UUID id, SubjectDTO dto);

    HttpEntity<?> deleteSubjectById(UUID id);
}
