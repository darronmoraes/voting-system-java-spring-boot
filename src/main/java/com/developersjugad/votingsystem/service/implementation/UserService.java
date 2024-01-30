package com.developersjugad.votingsystem.service.implementation;

import com.developersjugad.votingsystem.dto.UserDTO;
import com.developersjugad.votingsystem.exception.UserNotFoundException;
import com.developersjugad.votingsystem.model.Credential;
import com.developersjugad.votingsystem.model.User;
import com.developersjugad.votingsystem.model.pagination.UserPaginatedDTO;
import com.developersjugad.votingsystem.repository.CredentialRepository;
import com.developersjugad.votingsystem.repository.UserRepository;
import com.developersjugad.votingsystem.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepo;

    private final CredentialRepository credentialRepo;

    @Autowired
    public UserService(UserRepository userRepository, CredentialRepository credentialRepository) {
        this.userRepo = userRepository;
        this.credentialRepo = credentialRepository;
    }


    @Override
    public UserDTO getUserById(Long userId) {
        User user = null;
        Credential credential = null;

        try {
            user = getUserByUserId(userId);

            credential = credentialRepo.findById(user.getCredentialId()).get();
        } catch (NoSuchElementException e) {
            log.error("User Not Found Exception " + userId, e);
            throw new UserNotFoundException("User Not Found " + userId, e);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUser(user);
        userDTO.setEmail(credential.getEmail());
        userDTO.setUserName(credential.getUserName());

        return userDTO;
    }

    @Override
    public UserPaginatedDTO getAllUsers(Pageable pageable) {

        Page<User> userPage = userRepo.findAll(pageable);

        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user: userPage) {

            Credential credential = credentialRepo.findById(user.getCredentialId()).get();

            UserDTO userDTO = new UserDTO();

            userDTO.setUser(user);
            userDTO.setEmail(credential.getEmail());
            userDTO.setUserName(credential.getUserName());

            userDTOList.add(userDTO);

        }

        UserPaginatedDTO userPaginatedDTO = new UserPaginatedDTO();
        userPaginatedDTO.setUserDetails(userDTOList);
        userPaginatedDTO.setCount(userPage.getTotalElements());
        userPaginatedDTO.setPages(userPage.getTotalPages());

        return userPaginatedDTO;
    }

    @Override
    public void deleteUserById(Long userId) {
        User user = null;
        Credential credential = null;

        try {
            user = getUserByUserId(userId);

            credential = credentialRepo.findById(user.getCredentialId()).get();
        } catch (NoSuchElementException e) {
            log.error("User Not Found Exception " + userId, e);
            throw new UserNotFoundException("User Not Found " + userId, e);
        }

        userRepo.delete(user);

        credentialRepo.delete(credential);

    }

    public User getUserByUserId(Long userId) {
        return userRepo.findById(userId).get();
    }
}
