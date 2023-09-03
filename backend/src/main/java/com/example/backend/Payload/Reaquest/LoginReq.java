package com.example.backend.Payload.Reaquest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    private String username;
    private String password;
}
