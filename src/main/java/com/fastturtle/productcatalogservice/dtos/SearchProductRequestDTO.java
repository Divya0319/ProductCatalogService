package com.fastturtle.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductRequestDTO {

    private String query;

    private Integer pageLimit;

    private Integer pageNumber;
}
