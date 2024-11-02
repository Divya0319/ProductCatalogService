package com.fastturtle.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @Id
    private Long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private State state;     // this is needed because, when we delete a product from our system, in order to keep a way to bring it back,
                            // we actually don't delete it, but just mark it as inactive.
}
