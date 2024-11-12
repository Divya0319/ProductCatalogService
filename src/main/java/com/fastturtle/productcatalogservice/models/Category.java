package com.fastturtle.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Category extends BaseModel {

    private String name;

    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)  // in case of Eager fetch type, instead of separate queries, Hibernate will do join query of product and category
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 2)
    private List<Product> productList;


}
