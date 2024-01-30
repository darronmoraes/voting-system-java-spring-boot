package com.developersjugad.votingsystem.controller;

import com.developersjugad.votingsystem.dto.CreateElectionDTO;
import com.developersjugad.votingsystem.dto.ElectionDTO;
import com.developersjugad.votingsystem.service.implementation.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @PostMapping("/elections")
    public ResponseEntity<?> createElection(
            @RequestParam Long userId,
            @RequestBody CreateElectionDTO dto) {

        return new ResponseEntity<>(electionService.createAndSaveElection(userId, dto), HttpStatus.CREATED);
    }

    @GetMapping("/elections")
    public ResponseEntity<List<ElectionDTO>> getElections() {
        return new ResponseEntity<>(electionService.getAllElections(), HttpStatus.OK);
    }

    @GetMapping("/elections/{id}")
    public ResponseEntity<ElectionDTO> getElections(@PathVariable("id") Long electionId) {
        return new ResponseEntity<>(electionService.findElection(electionId), HttpStatus.OK);
    }

}
