package com.example.backend.Controller;

import com.example.backend.Repository.WeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/week")
@RequiredArgsConstructor
public class WeekController {
    private final WeekDayRepository weekDayRepository;
    @GetMapping
    public HttpEntity<?> getAllRooms(){
        return ResponseEntity.ok(weekDayRepository.findAll());
    }

}
