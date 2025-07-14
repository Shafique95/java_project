package com.example.beanconsole.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @GetMapping
String getAuthorDetails(){
    return "Author details are not available yet!";
}

}
