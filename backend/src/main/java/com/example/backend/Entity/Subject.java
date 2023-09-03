package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "subject")
@Entity
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToMany
    private List<Teacher> teachers;
    private LocalDateTime time;

    public Subject(String name, List<Teacher> teachers, LocalDateTime time) {
        this.name = name;
        this.teachers = teachers;
        this.time = time;
    }
}
