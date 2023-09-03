package com.example.backend.Repository;

import com.example.backend.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    List<Teacher> findAllByOrderByTime();
}
