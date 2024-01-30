package com.developersjugad.votingsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreateElectionDTO {

    private String name;
    private String type;
    private Date date;
    private Integer duration;
    private Time startTime;
    private Time endTime;
}
