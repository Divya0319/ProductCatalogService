package com.fastturtle.productcatalogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {

    private String company;
}
