package com.developersjugad.votingsystem.service;

import com.developersjugad.votingsystem.dto.CreateUserDTO;
import com.developersjugad.votingsystem.dto.UserDTO;

public interface IUserAuthService {

    UserDTO createUser(CreateUserDTO createUserDTO);

    UserDTO loginUser(String uniqueInput, String password);

    void removeUser(Long userId);

}
