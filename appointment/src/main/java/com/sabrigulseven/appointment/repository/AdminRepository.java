package com.sabrigulseven.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabrigulseven.appointment.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Admin findByIdentityNumberAndPassword(long identityNumber, String password);

}
