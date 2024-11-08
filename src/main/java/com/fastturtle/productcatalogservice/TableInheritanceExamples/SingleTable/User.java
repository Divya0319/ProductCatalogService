package com.fastturtle.productcatalogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    @Id
    private Long id;

    private String email;

    private String name;
}
