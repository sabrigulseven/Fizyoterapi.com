package com.sabrigulseven.appointment.model;

import javax.persistence.Entity;

@Entity
public class Physiotherapist extends User {

	private String profession;

	public Physiotherapist() {

	}

	public Physiotherapist(long id, String name, String profession, long identityNumber, String password) {
		super(id, identityNumber, password, name);
		this.profession = profession;

	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

}
