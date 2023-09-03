package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    @NotNull(message = "Name Should Not Be null@")
    @NotBlank(message = "Name Should Not Be Empty!")
    private String name;
    @NotNull(message = "Count Should Not Be null@")
    private Integer count;
}
