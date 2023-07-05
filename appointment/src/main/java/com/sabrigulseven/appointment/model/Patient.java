package com.sabrigulseven.appointment.model;

import javax.persistence.Entity;

@Entity
public class Patient extends User {

	private String emailAddress;

	public Patient() {
	}

	public Patient(long id, String name, long identityNumber, String password, String emailAddress) {
		super(id, identityNumber, password, name);
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
