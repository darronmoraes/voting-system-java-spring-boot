package com.developersjugad.votingsystem.model;

import com.developersjugad.votingsystem.constants.Constants;
import com.developersjugad.votingsystem.model.auditing.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = Constants.ELECTION_TABLE)
public class Election extends AuditModel implements Serializable {

    @Id
    @Column(name = Constants.ROW_ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;   // Required

    @NotEmpty(message = "name cannot be empty!")
    private String name;    // Required

    @NotEmpty(message = "Type cannot be empty!")
    private String type;    // Required

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Date of Election cannot be empty!")
    private Date date;  // Required

    @NotNull(message = "Duration of Election cannot be empty!")
    private Integer duration;

    @Temporal(TemporalType.TIME)
    @Column(name = Constants.START_TIME_COLUMN)
    private Time startTime;

    @Temporal(TemporalType.TIME)
    @Column(name = Constants.END_TIME_COLUMN)
    private Time endTime;

}
