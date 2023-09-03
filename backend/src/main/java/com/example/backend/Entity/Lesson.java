package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "lesson")
@Entity
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Group> group;



    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;
    @Column(nullable = false)
    private boolean type;
    @Column(nullable = false)
    private Integer para;

    @ManyToOne(fetch = FetchType.EAGER)
    private WeekDay weekDay;
    public Lesson(Subject subject, Room room, List<Group> group, Teacher teacher, boolean type) {
        this.subject = subject;
        this.room = room;
        this.group = group;
        this.teacher = teacher;
        this.type = type;
    }

}
