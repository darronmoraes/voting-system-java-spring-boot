package com.developersjugad.votingsystem.model;

import com.developersjugad.votingsystem.constants.Constants;
import com.developersjugad.votingsystem.model.auditing.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = Constants.USER_TABLE)
public class User extends AuditModel implements Serializable {

    @Id
    @Column(name = Constants.ROW_ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;   // Required

    @NotEmpty(message = "name cannot be empty!")
    private String name;    // Required

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    @NotNull(message = "Date of Birth cannot be empty!")
    private Date dateOfBirth;   // Required

    @NotEmpty(message = "Address cannot be empty!")
    private String address; // Required

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_joining")
    @NotNull(message = "Date of Joining cannot be empty!")
    private Date dateOfJoining; // Required

    @Size(max = 50)
    private String occupation;

    @Size(min = 4, max = 6)
    @NotEmpty(message = "Gender cannot be empty!")
    private String gender;  // Required

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approved_on")
    private Date approvedOn;

    @Column(name = "user_row_id")
    private Long approvedByUserId;

    private Boolean isApproved = false;

    @NotEmpty(message = "user role cannot be empty!")
    private String role;    // Required

    @Column(name = "credential_row_id")
    private Long credentialId;

}
