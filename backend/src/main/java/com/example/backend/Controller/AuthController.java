package com.example.backend.Controller;

import com.example.backend.Payload.Reaquest.LoginReq;
import com.example.backend.Services.AuthService.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginReq loginReq) throws InvalidCredentialsException {
//        System.out.println(loginReq);
        return service.login(loginReq);
    }



    @PostMapping("/refresh")
    public HttpEntity<?> refreshToken(@RequestParam String refreshToken) {
        return service.refreshToken(refreshToken);
    }
}
