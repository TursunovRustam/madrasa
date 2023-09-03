package com.example.backend.DTO;

import com.example.backend.Entity.Group;
import com.example.backend.Entity.Room;
import com.example.backend.Entity.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDTO {
    private List<UUID> groupIds;
    private boolean lessonType;
    private int para;
    private UUID roomId;
    private UUID subjectId;
    private UUID teacherId;
    private int weekId;
}
