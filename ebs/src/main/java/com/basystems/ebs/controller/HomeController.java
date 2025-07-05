package com.basystems.ebs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(HttpSession session,HttpServletRequest request,Model model) {
    	  
        model.addAttribute("title", "🔥 Sorna ERP");
        model.addAttribute("msg", "Enjoy the fire, rain, and green earth");
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/auth/login";  // লগইন না করলে রিডিরেক্ট
        }
        return "home";
    }
}