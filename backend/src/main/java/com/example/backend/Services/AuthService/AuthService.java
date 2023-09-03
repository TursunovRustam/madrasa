package com.example.backend.Services.AuthService;

import com.example.backend.Payload.Reaquest.LoginReq;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpEntity;

public interface AuthService {
    HttpEntity<?> login(LoginReq dto) throws InvalidCredentialsException;
    HttpEntity<?> refreshToken(String refreshToken);
}