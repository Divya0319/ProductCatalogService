package com.fastturtle.productcatalogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {

    private Long hours;
}
