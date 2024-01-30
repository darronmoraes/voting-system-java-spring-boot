package com.developersjugad.votingsystem.model.pagination;

import com.developersjugad.votingsystem.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPaginatedDTO extends PaginationDTO {

    private List<UserDTO> userDetails;
}
