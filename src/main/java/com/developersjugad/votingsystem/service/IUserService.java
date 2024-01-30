package com.developersjugad.votingsystem.service;

import com.developersjugad.votingsystem.dto.UserDTO;
import com.developersjugad.votingsystem.model.pagination.UserPaginatedDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    UserDTO getUserById(Long userId);

    UserPaginatedDTO getAllUsers(Pageable pageable);

    void deleteUserById(Long userId);
}
