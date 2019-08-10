package com.brainmentors.los.customer;

public class Address {
private String state;
private String city;
private String country;
private String pinCode;
public Address(String country, String city) {
	this.country=country;
	this.city=city;
}
public String getCity() {
	return city;
}
public String getCountry() {
	return country;
}
public String getState() {
	return state;
}
public void setCity(String city) {
	this.city = city;
}
public void setCountry(String country) {
	this.country = country;
}
public void setState(String state) {
	this.state = state;
}
public String getPinCode() {
	return pinCode;
}
public void setPinCode(String pinCode) {
	this.pinCode = pinCode;
}
}
