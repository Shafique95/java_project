package com.basystems.ebs.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.basystems.ebs.dto.LoginDto;
import com.basystems.ebs.dto.RegisterDto;
import com.basystems.ebs.entity.User;
import com.basystems.ebs.repository.UserRepository;

@Service
public class UserService {
  @Autowired private UserRepository repo;
  @Autowired private PasswordEncoder encoder;

  public void register(RegisterDto dto) {
	  User user = new User();
	  user.setFirstName(dto.getFirstName());
	  user.setLastName(dto.getLastName());
      user.setEmailOrPhone(dto.getEmailOrPhone());
	  user.setPassword(encoder.encode(dto.getPassword()));
	  user.setGender(dto.getGender());
	  user.setBirthDate(LocalDate.of(dto.getBirthYear(), dto.getBirthMonth(), dto.getBirthDay()));
	  repo.save(user);
	}


  @Autowired
  private PasswordEncoder passwordEncoder;

  public boolean authenticate(LoginDto dto) {
	  
      User user = repo.findFirstByEmailOrPhone(dto.getEmailOrPhone());
      System.out.println("db password "+ user.getPassword());
      System.out.println("user password from ui "+dto.getPassword());
      if (user == null) return false;

      // এটি গুরুত্বপূর্ণ – পাসওয়ার্ড ম্যাচিং এর সঠিক নিয়ম
      return passwordEncoder.matches(dto.getPassword(), user.getPassword());
  }

}
