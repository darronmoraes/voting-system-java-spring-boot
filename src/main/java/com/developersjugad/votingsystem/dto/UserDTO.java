package com.developersjugad.votingsystem.dto;

import com.developersjugad.votingsystem.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private User user;  // user data

    private String email;   // credential data

    private String userName;    // credential data

}
