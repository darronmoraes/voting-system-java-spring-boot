package com.developersjugad.votingsystem.controller;

import com.developersjugad.votingsystem.dto.UserDTO;
import com.developersjugad.votingsystem.model.pagination.UserPaginatedDTO;
import com.developersjugad.votingsystem.service.IUserAuthService;
import com.developersjugad.votingsystem.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserPaginatedDTO> getPaginatedUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.getAllUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> userById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
}
