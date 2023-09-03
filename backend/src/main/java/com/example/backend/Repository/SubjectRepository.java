package com.example.backend.Repository;

import com.example.backend.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    List<Subject> findAllByOrderByTime();
}
