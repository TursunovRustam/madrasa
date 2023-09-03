package com.example.backend.Controller;

import com.example.backend.DTO.UserDTO;
import com.example.backend.Entity.User;
import com.example.backend.Services.UsersService.CurrentUser;
import com.example.backend.Services.UsersService.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService service;

    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser User user){
        System.out.println(user );
        return service.getMe(user);
    }
}
