package com.fastturtle.productcatalogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "tpc_user")
public class User {

    @Id
    private long id;

    private String email;

    private String name;
}
