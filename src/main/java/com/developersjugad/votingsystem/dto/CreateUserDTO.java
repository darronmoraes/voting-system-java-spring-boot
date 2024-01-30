package com.developersjugad.votingsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateUserDTO {

    private String name;

    private Date dateOfBirth;

    private String address;

    private Date dateOfJoining;

    private String occupation;

    private String gender;

    private String role;

    private String email;

    private String userName;

    private String password;
}
