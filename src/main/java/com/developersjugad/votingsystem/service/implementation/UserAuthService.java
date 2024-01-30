package com.developersjugad.votingsystem.service.implementation;

import com.developersjugad.votingsystem.dto.CreateUserDTO;
import com.developersjugad.votingsystem.dto.UserDTO;
import com.developersjugad.votingsystem.exception.UserNotApprovedException;
import com.developersjugad.votingsystem.exception.UserRegisterException;
import com.developersjugad.votingsystem.model.Credential;
import com.developersjugad.votingsystem.model.User;
import com.developersjugad.votingsystem.repository.CredentialRepository;
import com.developersjugad.votingsystem.repository.UserRepository;
import com.developersjugad.votingsystem.service.IUserAuthService;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
@Transactional
public class UserAuthService implements IUserAuthService {

    private final UserRepository userRepo;

    private final CredentialRepository credentialRepo;

    private final UserService userService;

    @Autowired
    public UserAuthService(UserRepository userRepository, CredentialRepository credentialRepository, UserService userService) {
        this.userRepo = userRepository;
        this.credentialRepo = credentialRepository;
        this.userService = userService;
    }

    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        log.info("Creating User {}", createUserDTO);

        UserDTO userDTO = null;

        // Step 1: Set the User & Credential Entities
        User user = mapUserDTOtoUser(createUserDTO);
        Credential credential = mapUserDTOtoCredential(createUserDTO);

        try {
            // Step 2: Save Credential to Repo
            credential = credentialRepo.save(credential);
            log.info("Credential {}", credential);

            if (credential.getRowId() == null) {
                throw new UserRegisterException("User registration failed");
            }

            userDTO = new UserDTO();

            userDTO.setEmail(credential.getEmail());
            userDTO.setUserName(credential.getUserName());

            // Step 3: Fetch saved credential-id
            Long credentialId = credential.getRowId();

            // Step 4: Set credential-id to User
            user.setCredentialId(credentialId);

            // Set as approved for System Admin
            if (user.getRole().equals("System Admin")) {
                user.setIsApproved(true);

                /*LocalDateTime now = LocalDateTime.now();
                user.setApprovedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));*/
            }

            // Step 5: Save User to Repo
            user = userRepo.save(user);
            log.info("User {}", user);

            // Step 6: Set UserDTO and return UserDTO
            userDTO.setUser(user);

        } catch (DataIntegrityViolationException e) {

            log.error("Email or Username Already exists : " + e.getMessage());
          throw new UserRegisterException("User registration failed : Email or Username Already exists", e);

        } catch (UserRegisterException e) {

            log.error("User registration failed : " + e.getMessage());
            throw new UserRegisterException("User registration failed", e);

        }

        return userDTO;
    }

    @Override
    public UserDTO loginUser(String uniqueInput, String password) {

        log.info("Input : " + uniqueInput);
        log.warn("Password : " + password);

        Credential credential = credentialRepo.findByEmailOrUsername(uniqueInput);
        log.info("Credential {}", credential);

        // Fetch user data from user repo
        User user = userRepo.findUserByCredentialId(credential.getRowId());
        log.info("User {}", user);

        if (user.getIsApproved() == null || !user.getIsApproved()) {
            log.warn("User Registered, Not approved");  // Throw UserNotApprovedException
            throw new UserNotApprovedException("User Approval Pending Exception");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUser(user);
        userDTO.setUserName(credential.getUserName());
        userDTO.setEmail(credential.getEmail());

        return userDTO;
    }

    @Override
    public void removeUser(Long userId) {
        log.info("UserId : " + userId);

        log.info("Deleting User");
        userService.deleteUserById(userId);

        log.info("Deleted User");
    }

    private User mapUserDTOtoUser(CreateUserDTO createUserDTO) {
        User user = new User();

        user.setName(createUserDTO.getName());
        user.setDateOfBirth(createUserDTO.getDateOfBirth());
        user.setAddress(createUserDTO.getAddress());
        user.setDateOfJoining(createUserDTO.getDateOfJoining());
        user.setOccupation(createUserDTO.getOccupation());
        user.setGender(createUserDTO.getGender());
        user.setRole(createUserDTO.getRole());

        return user;
    }

    private Credential mapUserDTOtoCredential(CreateUserDTO createUserDTO) {
        Credential credential = new Credential();

        credential.setEmail(createUserDTO.getEmail());
        credential.setUserName(createUserDTO.getUserName());
        credential.setPassword(createUserDTO.getPassword());

        return credential;
    }
}
