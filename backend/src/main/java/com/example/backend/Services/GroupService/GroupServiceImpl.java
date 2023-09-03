package com.example.backend.Services.GroupService;

import com.example.backend.DTO.GroupDTO;
import com.example.backend.Entity.Group;
import com.example.backend.Entity.Lesson;
import com.example.backend.Entity.WeekDay;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Repository.LessonRepository;
import com.example.backend.Repository.WeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final LessonRepository lessonRepository;
    private final WeekDayRepository weekDayRepository;
    @Override
    public HttpEntity<?> getGroups() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public HttpEntity<?> addGroups(GroupDTO DTO) {
        Group savedGroup = repository.save(new Group(null, DTO.getName(), DTO.getCount()));

        List<Group> groups = new ArrayList<>();
        List<Lesson> lessons = new ArrayList<>();
        groups.add(savedGroup);
        for (WeekDay weekDay : weekDayRepository.findAll()) {
            for (int i = 1;i <= 4;i++) {
                lessons.add(new Lesson(
                        null,
                        null,
                        null,
                        groups,
                        null,
                        false,
                        i,
                        weekDay
                ));
            };
        }

        lessonRepository.saveAll(lessons);
        return ResponseEntity.ok(savedGroup);
    }

    @Override
    public HttpEntity<?> editGroup(UUID id, GroupDTO dto) {
        repository.save(new Group(id,dto.getName(),dto.getCount()));
        return ResponseEntity.ok(dto);
    }
    @Override
    public HttpEntity<?> deleteGroupById(UUID id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully!");
    }

    @Override
    public Object findByGroupId(UUID groupId) {
        Optional<Group> byId = repository.findById(groupId);
        if(byId.isPresent()){
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.ok("gruh mavjud emas");
    }
}
