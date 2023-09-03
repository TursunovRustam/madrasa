package com.example.backend.Repository;

import com.example.backend.Entity.WeekDay;
import com.example.backend.Enums.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayRepository extends JpaRepository<WeekDay, Integer> {
    WeekDay findByName(WeekDays name);
}
