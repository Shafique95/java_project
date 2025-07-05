package com.basystems.ebs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basystems.ebs.dto.LoginDto;
import com.basystems.ebs.dto.RegisterDto;
import com.basystems.ebs.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/auth")
public class AuthController {
  @Autowired private UserService userService;

  @GetMapping("/login")
  public String loginPage() {
    return "auth/login";
  }

  @GetMapping("/register")
  public String registerPage() {
    return "auth/register";
  }

  @PostMapping("/register")
  public String registerUser(RegisterDto dto) {
	  System.out.println(dto);
    userService.register(dto);
    return "redirect:/auth/login?success";
  }

  @PostMapping("/login")
  public String loginUser(LoginDto dto, HttpSession session, Model model) {
    System.out.println("Login attempt: " + dto.getEmailOrPhone());
    System.out.println("user password is "+ dto.getPassword());

    boolean authenticated = userService.authenticate(dto);
    System.out.println("checkign auth"+authenticated);
 

    if (authenticated) {
      // Manually set user session attribute (you can set user details here)
      session.setAttribute("loggedInUser", dto.getEmailOrPhone());
      return "redirect:/home";
    } else {
      model.addAttribute("error", "Invalid email or password");
      return "auth/login";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/auth/login?logout=true";
  }

}
