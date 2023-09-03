package com.example.backend.Repository;

import com.example.backend.Entity.Room;
import com.example.backend.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    List<Room> findAllByOrderByTime();
}
