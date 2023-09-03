package com.example.backend.Services.LessonService;

import com.example.backend.DTO.LessonDTO;
import com.example.backend.Entity.Group;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface LessonService {

    HttpEntity<?> addLesson(LessonDTO lesson, UUID currentGroup);

    HttpEntity<?> editLessonOfGroup(UUID groupId,LessonDTO lessonDTO);

    Object findByGroupId(UUID groupId);
}
