package com.sabrigulseven.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabrigulseven.appointment.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	public Patient findByIdentityNumberAndPassword(long identityNumber, String password);
	//@query("Select Patient* FROM Patient Where Patient.id=identityNumber and Patient.id=password")
}
