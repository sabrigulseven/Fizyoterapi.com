package com.sabrigulseven.appointment.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	
	public Admin() {
		
	}

	public Admin(long id, long identityNumber, String password, String name) {
		super(id, identityNumber, password, name);
	}
	
}
