package com.example.backend.DTO;

import com.example.backend.Entity.Teacher;
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
public class SubjectDTO {
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotNull(message = "Teacher cannot be null")
    @NotBlank(message = "Teacher cannot be empty")
    private List<UUID> teacherIds;
}
