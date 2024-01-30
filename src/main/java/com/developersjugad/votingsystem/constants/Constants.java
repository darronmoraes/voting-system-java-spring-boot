package com.developersjugad.votingsystem.constants;

public class Constants {

    // Tables
    public static final String USER_TABLE = "user";
    public static final String CREDENTIAL_TABLE = "credential";
    public static final String ELECTION_TABLE = "election";

    // Columns
    public static final String ROW_ID_COLUMN = "row_id";
    public static final String CREATED_AT_COLUMN = "created_at";
    public static final String UPDATED_AT_COLUMN = "updated_at";
    public static final String USER_NAME_COLUMN = "user_name";
    public static final String START_TIME_COLUMN = "start_time";
    public static final String END_TIME_COLUMN = "end_time";

    // Empty
    public static final String EMPTY_EMAIL = "email cannot be empty!";
    public static final String EMPTY_USER_NAME = "username cannot be empty!";
    public static final String EMPTY_PASSWORD = "password cannot be empty!";

    // Role
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_SYSTEM_ADMIN = "SYSTEM ADMIN";
    public static final String ROLE_MEMBER = "MEMBER";
}
