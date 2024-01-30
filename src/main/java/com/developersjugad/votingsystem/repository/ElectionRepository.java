package com.developersjugad.votingsystem.repository;

import com.developersjugad.votingsystem.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}
