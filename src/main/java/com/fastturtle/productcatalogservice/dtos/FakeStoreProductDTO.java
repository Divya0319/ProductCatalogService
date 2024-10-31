package com.fastturtle.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {

    private Long id;

    private String title;

    private Double price;

    private String description;

    private String category;

    private String image;

    private FakeStoreRatingDTO fakeStoreRatingDTO;
}
