package com.example.backend.Loaders;

import com.example.backend.Entity.Role;
import com.example.backend.Entity.User;
import com.example.backend.Entity.WeekDay;
import com.example.backend.Enums.RoleEnum;
import com.example.backend.Enums.WeekDays;
import com.example.backend.Repository.LessonRepository;
import com.example.backend.Repository.RoleRepository;
import com.example.backend.Repository.UsersRepository;
import com.example.backend.Repository.WeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultDatasLoader implements CommandLineRunner {

    private final WeekDayRepository weekDayRepository;
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final LessonRepository lessonRepository;

    @Override
    public void run(String... args) {
        String superVisorName = "admin";
        if (
                roleRepository.findByRoleName(RoleEnum.ROLE_SUPER_VISOR.name()) == null
                        && usersRepository.findByUsername(superVisorName).isEmpty()
        ) {
            Role savedRole = roleRepository.save(
                    Role.builder()
                            .roleName(RoleEnum.ROLE_SUPER_VISOR.name())
                            .build()
            );
            List<Role> roles = new ArrayList<>();
            roles.add(savedRole);
            User superVisor = usersRepository.save(
                    new User(
                            null,
                            "admin",
                            passwordEncoder.encode("root123"),
                            roles
                    )
            );
        }


        List<WeekDay> allWeekDays = new ArrayList<>();
        if (weekDayRepository.count() == 0) {
            List<WeekDays> weekDaysList = Arrays.asList(WeekDays.values());
            for (WeekDays weekDays : weekDaysList) {
                if (!weekDays.equals(WeekDays.SUNDAY)) {
                    allWeekDays.add(new WeekDay(null, weekDays));
                }
            }
        }
        weekDayRepository.saveAll(allWeekDays);


    }


}
