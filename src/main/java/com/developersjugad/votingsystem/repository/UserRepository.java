package com.developersjugad.votingsystem.repository;

import com.developersjugad.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.credentialId = :credentialId")
    User findUserByCredentialId(Long credentialId);
}
