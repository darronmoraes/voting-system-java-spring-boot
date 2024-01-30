package com.developersjugad.votingsystem.service.implementation;

import com.developersjugad.votingsystem.constants.Constants;
import com.developersjugad.votingsystem.dto.CreateElectionDTO;
import com.developersjugad.votingsystem.dto.ElectionDTO;
import com.developersjugad.votingsystem.dto.UserDTO;
import com.developersjugad.votingsystem.exception.AuthorizationException;
import com.developersjugad.votingsystem.exception.ElectionNotFoundException;
import com.developersjugad.votingsystem.exception.UserNotApprovedException;
import com.developersjugad.votingsystem.exception.UserNotFoundException;
import com.developersjugad.votingsystem.model.Election;
import com.developersjugad.votingsystem.repository.ElectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ElectionService {

    private final ElectionRepository electionRepo;

    private final ModelMapper mapper;

    private final UserService userService;

    @Autowired
    public ElectionService(ElectionRepository electionRepo, ModelMapper mapper, UserService userService) {
        this.electionRepo = electionRepo;
        this.mapper = mapper;
        this.userService = userService;
    }

    // create new election and save to repo
    public ElectionDTO createAndSaveElection(Long userId, CreateElectionDTO dto) {
        log.info("param userId : " + userId);
        log.info("param dto : {}", dto);

        ElectionDTO electionDTO = null;

        try {
            UserDTO user = userService.getUserById(userId);

            if (!user.getUser().getIsApproved()) {
                log.error("User Approval Pending " + userId);
                throw new UserNotApprovedException("Request approval");
            }

            if (!user.getUser().getRole().equals(Constants.ROLE_ADMIN)) {
                log.error("Not Authorized To Perform this Action " + userId);
                throw new AuthorizationException("Not Authorized To Perform this Action");
            }

            // convert dto to entity
            Election election = mapper.map(dto, Election.class);

            // save entity to repo
            election = electionRepo.save(election);
            log.info("election : {}", election);

            // convert saved entity to dto
            electionDTO = mapper.map(election, ElectionDTO.class);

        } catch (NoSuchElementException e) {
            log.error("User Not Found Exception " + userId, e);
            throw new UserNotFoundException("User Not Found " + userId, e);
        }

        // return dto
        return electionDTO;
    }

    public List<ElectionDTO> getAllElections() {
        List<ElectionDTO> electionList = new ArrayList<>();

        List<Election> elections = electionRepo.findAll();

        for (Election e : elections) {
            ElectionDTO electionDTO = mapper.map(e, ElectionDTO.class);
            electionList.add(electionDTO);
        }

        return electionList;
    }

    public ElectionDTO findElection(Long electionId) {

        ElectionDTO dto = null;

        try {
            Election election = getElectionById(electionId);

            dto = mapper.map(election, ElectionDTO.class);

        } catch (NoSuchElementException e) {
            log.error("Election Not Found Exception " + electionId, e);
            throw new ElectionNotFoundException("Election Not Found " + electionId, e);
        }

        return dto;
    }

    private Election getElectionById(Long electionId) {
        return electionRepo.findById(electionId).get();
    }
}
