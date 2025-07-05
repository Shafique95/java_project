package com.basystems.ebs.dto;

public class RegisterDto {
	  private String firstName;
	  private String lastName;
	  private String emailOrPhone;
	  private String password;
	  private String gender;
	  private int birthDay;
	  private int birthMonth;
	  private int birthYear;

	  // Getters and Setters
	  public String getFirstName() { return firstName; }
	  public void setFirstName(String firstName) { this.firstName = firstName; }

	  public String getLastName() { return lastName; }
	  public void setLastName(String lastName) { this.lastName = lastName; }

	  public String getEmailOrPhone() { return emailOrPhone; }
	  public void setEmailOrPhone(String emailOrPhone) { this.emailOrPhone = emailOrPhone; }

	  public String getPassword() { return password; }
	  public void setPassword(String password) { this.password = password; }

	  public String getGender() { return gender; }
	  public void setGender(String gender) { this.gender = gender; }

	  public int getBirthDay() { return birthDay; }
	  public void setBirthDay(int birthDay) { this.birthDay = birthDay; }

	  public int getBirthMonth() { return birthMonth; }
	  public void setBirthMonth(int birthMonth) { this.birthMonth = birthMonth; }

	  public int getBirthYear() { return birthYear; }
	  public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
	}
