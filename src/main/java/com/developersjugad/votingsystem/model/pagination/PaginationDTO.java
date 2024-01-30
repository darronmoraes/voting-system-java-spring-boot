package com.developersjugad.votingsystem.model.pagination;

import lombok.Data;

@Data
public class PaginationDTO {

    private Integer pages;
    private Long count;
}
