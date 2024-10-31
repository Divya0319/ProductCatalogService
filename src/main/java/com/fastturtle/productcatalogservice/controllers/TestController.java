package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.models.TestUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring Boot";
    }

    @GetMapping("/users")
    public TestUser getUser() {
        return new TestUser("Ramnarayana", "ram@gmail.com");
    }
}
