package com.example.backend.Repository;

import com.example.backend.Entity.Group;
import com.example.backend.Entity.Lesson;
import com.example.backend.Enums.WeekDays;
import com.example.backend.Projection.LessonProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    @Query(value = """
            SELECT  l.id FROM lesson_group lg
                     inner join lesson l on lg.lesson_id = l.id
                     inner join week w on l.week_day_id = w.id
            where lg.group_id=:groupid
            and w.name=:dayOfWeek and l.para=:para
            """,nativeQuery = true)
    Optional<LessonProjection> findByGroupAndRoomAndParaAndWeekDay(UUID groupid, Integer para, String dayOfWeek);

    List<Lesson> findByGroup(Group group);
}
