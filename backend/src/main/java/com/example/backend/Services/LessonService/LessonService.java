package com.example.backend.Services.LessonService;

import com.example.backend.DTO.LessonDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface LessonService {

    HttpEntity<?> addLesson(LessonDTO lesson);

    HttpEntity<?> editLessonOfGroup(UUID groupId,LessonDTO lessonDTO);

    Object findByGroupId(UUID groupId);
}
