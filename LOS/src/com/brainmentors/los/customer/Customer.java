package com.brainmentors.los.customer;

public class Customer {
public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
private int id;
private int stage;
private String remarks;

PersonalInformation personal; //has-A
LoanDetails loandetails;



public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public LoanDetails getLoandetails() {
	return loandetails;
}
public void setLoandetails(LoanDetails loandetails) {
	this.loandetails = loandetails;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public PersonalInformation getPersonal() {
	return personal;
}
public void setPersonal(PersonalInformation personal) {
	this.personal = personal;
}
public double getIncome() {
	return income;
}
public void setIncome(double income) {
	this.income = income;
}
public double getLiability() {
	return liability;
}
public void setLiability(double liability) {
	this.liability = liability;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
private double income;
private double liability;
private Address address;
}
