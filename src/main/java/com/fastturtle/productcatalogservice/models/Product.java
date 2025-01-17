package com.fastturtle.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Product extends BaseModel {

    private String name;

    private String imageUrl;

    private String description;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Category category;

    private Boolean isPrimeSpecific;

    private Boolean isPublic = false;

}
