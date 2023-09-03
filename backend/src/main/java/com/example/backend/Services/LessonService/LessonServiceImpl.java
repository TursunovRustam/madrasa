package com.example.backend.Services.LessonService;

import com.example.backend.DTO.LessonDTO;
import com.example.backend.Entity.*;
import com.example.backend.Enums.WeekDays;
import com.example.backend.Projection.LessonProjection;
import com.example.backend.Repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final RoomRepository roomRepository;
    private final WeekDayRepository weekDayRepository;

    @Override
    public HttpEntity<?> addLesson(LessonDTO lessonDTO, UUID currentGroup) {

        Lesson lesson = Lesson.builder()
                .weekDay(weekDayRepository.findById(lessonDTO.getWeekId()).orElseThrow())
                .subject(subjectRepository.findById(lessonDTO.getSubjectId()).orElseThrow())
                .teacher(teacherRepository.findById(lessonDTO.getTeacherId()).orElseThrow())
                .group(lessonDTO.isLessonType()?groupRepository.findAllById(lessonDTO.getGroupIds()): List.of(groupRepository.findById(currentGroup).orElseThrow()))
                .para(lessonDTO.getPara())
                .room(roomRepository.findById(lessonDTO.getRoomId()).orElseThrow())
                .type(lessonDTO.isLessonType()).build();
        return ResponseEntity.ok(lessonRepository.save(lesson));
    }

    @Transactional
    @Override
    public HttpEntity<?> editLessonOfGroup(UUID groupId, LessonDTO lessonDTO) {
//        LessonProjection editingLesson = isNewElement(lessonDTO);
//        Room roomById = lessonDTO.getRoomId() == null ? null : roomRepository.findById(lessonDTO.getRoomId()).orElseGet(null);
//        Subject subjectById = lessonDTO.getSubjectId() == null ? null : subjectRepository.findById(lessonDTO.getSubjectId()).orElseGet(null);
//        Teacher teacherById = lessonDTO.getTeacherId() == null ? null : teacherRepository.findById(lessonDTO.getTeacherId()).orElseGet(null);
//        Group groupsById = groupRepository.findById(lessonDTO.getGroupId()).orElseGet(null);
//        WeekDay byName = weekDayRepository.findByName(WeekDays.valueOf(generateWeekDay(lessonDTO.getWeekday())));
//        System.out.println(editingLesson);
//        if (editingLesson == null) {
//            List<Group> groups = new ArrayList<>();
//            groups.add(groupsById);
//            return ResponseEntity.ok(lessonRepository.save(new Lesson(
//                    null,
//                    subjectById,
//                    roomById,
//                    groups,
//                    teacherById,
//                    false,
//                    lessonDTO.getPara(),
//                    byName
//            )));
//        } else {
//            Lesson editingLessonReal = lessonRepository.findById(editingLesson.getId()).get();
//            editingLessonReal.setPara(lessonDTO.getPara());
//            if (roomById != null) {
//                editingLessonReal.setRoom(roomById);
//            }
//            editingLessonReal.setType(false);
//            editingLessonReal.setWeekDay(byName);
//            if (teacherById != null) {
//                editingLessonReal.setTeacher(teacherById);
//            }
//            if(subjectById!=null) {
//            editingLessonReal.setSubject(subjectById);
//            }
//            return ResponseEntity.ok(lessonRepository.save(editingLessonReal));
        return null;
    }

    @Override
    public Object findByGroupId(UUID groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        return ResponseEntity.ok(lessonRepository.findByGroup(group));
    }

//    private LessonProjection isNewElement(LessonDTO lessonDTO) {
//        String s = generateWeekDay(lessonDTO.getWeekId());
//        Optional<LessonProjection> byGroupAndRoomAndParaAndWeekDay = lessonRepository.findByGroupAndRoomAndParaAndWeekDay(lessonDTO.getGroupId(), lessonDTO.getPara(), s);
//        return byGroupAndRoomAndParaAndWeekDay.isPresent() ? byGroupAndRoomAndParaAndWeekDay.get() : null;
//    }

    private String generateWeekDay(Integer weekId) {
        WeekDay weekDay = weekDayRepository.findById(weekId).orElseThrow();
        String weekDayReal = "";
        switch (weekDay.getName().toString()) {
            case "DUSHANBA":
                weekDayReal = "MONDAY";
                break;
            case "SESHANBA":
                weekDayReal = "TUESDAY";
                break;
            case "CHORSHANBA":
                weekDayReal = "WEDNESDAY";
                break;
            case "PAYSHANBA":
                weekDayReal = "THURSDAY";
                break;
            case "JUMA":
                weekDayReal = "FRIDAY";
                break;
            case "SHANBA":
                weekDayReal = "SATURDAY";
                break;
        }
        return weekDayReal;
    }
}
