package com.sabrigulseven.entity;

public class Physiotherapist {
	
	private long id;

	private String name;
	
	private String profession;

	public Physiotherapist() {

	}

	public Physiotherapist(long id, String name, String profession) {
		this.id = id;
		this.name = name;
		this.profession = profession;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	
}
