package com.example.backend.Repository;

import com.example.backend.Entity.User;
import com.example.backend.Projection.UsersProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
