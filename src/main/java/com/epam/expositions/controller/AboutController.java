package com.epam.expositions.controller;

import com.epam.expositions.exception.InvalidDataException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String aboutForward(){
        return "about";
    }

    @PostMapping("/about")
    public String aboutPost(){
        throw new InvalidDataException("lol");
    }
}
