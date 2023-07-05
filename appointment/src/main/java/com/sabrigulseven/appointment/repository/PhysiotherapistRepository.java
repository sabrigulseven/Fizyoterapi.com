package com.sabrigulseven.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;

public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {

	Physiotherapist findByIdentityNumberAndPassword(long identityNumber, String password);

	@Query("SELECT DISTINCT a.patient FROM Appointment a WHERE a.physiotherapist = :physiotherapist")
	List<Patient> findPatientsByPhysiotherapist(@Param("physiotherapist") Physiotherapist physiotherapist);
}
