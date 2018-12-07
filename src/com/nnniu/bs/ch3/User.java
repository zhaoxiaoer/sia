package com.nnniu.bs.ch3;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private String name;
	private String lastname;
	private String password;
	private String detail;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthDate;
	private String country;
	private Gender gender;
	private boolean nonSmoking;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public boolean isNonSmoking() {
		return nonSmoking;
	}
	public void setNonSmoking(boolean nonSmoking) {
		this.nonSmoking = nonSmoking;
	}
}
