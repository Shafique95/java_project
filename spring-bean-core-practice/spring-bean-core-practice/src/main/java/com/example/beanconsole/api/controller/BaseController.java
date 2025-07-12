package com.example.beanconsole.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/base")
public class BaseController {
       @GetMapping
public String checkBaseController(){
    return "Base Controller is working for api requests!";
    }

}
