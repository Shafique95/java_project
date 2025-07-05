package com.basystems.ebs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("title", "🔥 Sorna ERP");
        model.addAttribute("msg", "Enjoy the fire, rain, and green earth");
        return "home";
    }
}