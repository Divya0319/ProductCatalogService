package com.fastturtle.productcatalogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    private long id;

    private String email;

    private String name;
}
