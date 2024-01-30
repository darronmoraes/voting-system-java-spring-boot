package com.developersjugad.votingsystem.repository;

import com.developersjugad.votingsystem.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    @Query("SELECT c FROM Credential c WHERE c.email = :uniqueInput OR c.userName = :uniqueInput")
    Credential findByEmailOrUsername(String uniqueInput);
}
