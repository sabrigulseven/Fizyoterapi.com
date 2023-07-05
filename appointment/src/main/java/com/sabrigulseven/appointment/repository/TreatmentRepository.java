package com.sabrigulseven.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabrigulseven.appointment.model.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

}
