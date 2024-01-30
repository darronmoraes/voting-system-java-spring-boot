package com.developersjugad.votingsystem.controller;

import com.developersjugad.votingsystem.dto.CreateUserDTO;
import com.developersjugad.votingsystem.dto.UserDTO;
import com.developersjugad.votingsystem.model.response.ErrorResponse;
import com.developersjugad.votingsystem.service.IUserAuthService;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@Slf4j
public class UserAuthController {

    @Autowired
    private IUserAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody CreateUserDTO createUserDTO) {
        return new ResponseEntity<>(authService.createUser(createUserDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam(required = false) String email,
            @RequestParam String userName,
            @RequestParam String password) {

        log.info("Email : " + email);
        log.info("Username : " + userName);

        UserDTO userDTO = null;

        if (email != null || userName != null) {

            if (email == null) {

                userDTO = authService.loginUser(userName, password);

            } else {

                userDTO = authService.loginUser(email, password);

            }
        }

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userId}/remove")
    public ResponseEntity<?> removeUser(@PathVariable Long userId) {
        authService.removeUser(userId);

        return ResponseEntity.ok(Map.of("message", "User " + userId + " remove success", "success", true));
    }

}
