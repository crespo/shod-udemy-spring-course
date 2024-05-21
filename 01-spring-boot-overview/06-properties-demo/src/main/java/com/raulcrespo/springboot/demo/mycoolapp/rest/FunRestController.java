package com.raulcrespo.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // injecting properties from application.properties file

    @Value("${coach.name}")
    private String coachName;
    @Value("${team.name}")
    private String teamName;

    // expose endpoint for "teaminfo"
    @GetMapping("/teaminfo")
    public String getTeam() {
        return "Coach: " + coachName + " Team: " + teamName;
    }

    // expose "/" that return "Hello World!"
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/easteregg")
    public String easterEgg() {
        return "Oh! You found me.";
    }

}
