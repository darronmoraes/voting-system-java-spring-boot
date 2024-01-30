package com.developersjugad.votingsystem.model;

import com.developersjugad.votingsystem.constants.Constants;
import com.developersjugad.votingsystem.model.auditing.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = Constants.CREDENTIAL_TABLE)
public class Credential extends AuditModel implements Serializable {

    @Id
    @Column(name = Constants.ROW_ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;   // Required

    @Column(unique = true)
    @NotEmpty(message = Constants.EMPTY_EMAIL)
    private String email;    // Required

    @Column(name = Constants.USER_NAME_COLUMN, unique = true)
    @NotEmpty(message = Constants.EMPTY_USER_NAME)
    private String userName;    // Required

    @NotEmpty(message = Constants.EMPTY_PASSWORD)
    private String password;    // Required
}
