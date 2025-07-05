package com.basystems.ebs.dto;

 

public class LoginDto {
  private String emailOrPhone;
  private String password;

  // Getter & Setter
  public String getEmailOrPhone() {
    return emailOrPhone;
  }

  public void setEmailOrPhone(String emailOrPhone) {
    this.emailOrPhone = emailOrPhone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
