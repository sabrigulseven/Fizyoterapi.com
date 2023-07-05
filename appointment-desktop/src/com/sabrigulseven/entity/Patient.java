package com.sabrigulseven.entity;

public class Patient {
	
	private long id;

	private long identityNumber;
	
	private String name;
	
	public Patient() {
	}

	public Patient(long id, long identityNumber, String name) {
		this.id = id;
		this.identityNumber = identityNumber;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(long identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
