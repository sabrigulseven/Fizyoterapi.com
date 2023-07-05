package com.sabrigulseven.appointment.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	
	List<Appointment> findAll(Specification<Appointment> spec, Sort sort);

	List<Appointment> findByPhysiotherapistAndDateAndTimeBetweenOrderByDateAscTimeAsc(Physiotherapist physiotherapist, LocalDate date,
			LocalTime startTime, LocalTime endTime);

	List<Appointment> findByPhysiotherapistAndDateOrderByDateAscTimeAsc(Physiotherapist physiotherapist, LocalDate date);

	List<Appointment> findByPhysiotherapistAndDateAfterOrderByDateAscTimeAsc(Physiotherapist physiotherapist, LocalDate date);
	
	List<Appointment> findByPhysiotherapistAndDateAfterAndPatientIsNullOrderByDateAscTimeAsc(Physiotherapist physiotherapist, LocalDate date);

	List<Appointment> findByPatientOrderByDateAscTimeAsc(Patient patient);

	List<Appointment> findByPatientAndDateAfterOrderByDateAscTimeAsc(Patient patient, LocalDate date);

	List<Appointment> findByPatientAndDateBeforeOrderByDateDesc(Patient patient, LocalDate date);

	List<Appointment> findByPatientAndDateOrderByDateAscTimeAsc(Patient patient, LocalDate date);

	Appointment findAppointmentByPatientAndPhysiotherapistAndDate(Patient patient, Physiotherapist physiotherapist,LocalDate date);

}
