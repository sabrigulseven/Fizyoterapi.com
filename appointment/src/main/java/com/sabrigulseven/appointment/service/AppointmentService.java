package com.sabrigulseven.appointment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;

public interface AppointmentService {

	/**
	 * Retrieves a list of appointments based on the specified criteria.
	 *
	 * @param physiotherapistId The ID of the physiotherapist.
	 *                          If provided, only appointments assigned to this
	 *                          physiotherapist will be included in the result.
	 * @param startDate         The start date. If provided, only appointments on or
	 *                          after this date will be included in the result.
	 * @param endDate           The end date. If provided, only appointments on or
	 *                          before this date will be included in the result.
	 * @return A list of appointments that match the specified criteria.
	 */
	List<Appointment> getAppointmentList(Long physiotherapistId, LocalDate startDate, LocalDate endDate);

	/**
	 * Retrieves a list of available appointments for a specific physiotherapist.
	 *
	 * @param physiotherapist The physiotherapist for whom to retrieve the
	 *                        appointments.
	 * @return A list of available to register appointments for the specified physiotherapist.
	 */
	List<Appointment> getAvailableAppointmentsForPhysiotherapist(Physiotherapist physiotherapist);

	/**
	 * Retrieves an appointment by its ID.
	 * 
	 * @param appointmentId The ID of the appointment to retrieve.
	 * @return An optional containing the appointment with the specified ID,
	 *         or an empty optional if no appointment is found.
	 */
	Optional<Appointment> getById(long appointmentId);

	/**
	 * Saves an appointment.
	 * 
	 * @param appointment The appointment to be saved.
	 * @return Saved appointment.
	 */
	Appointment save(Appointment appointment);

	/**
	 * Retrieves an appointment based on the specified patient, physiotherapist, and
	 * date.
	 * 
	 * @param patient         The patient associated with the appointment.
	 * @param physiotherapist The physiotherapist associated with the appointment.
	 * @param date            The date of the appointment.
	 * @return The appointment matching the given parameters, if found.
	 */
	Appointment getAppointment(Patient patient, Physiotherapist physiotherapist, LocalDate date);

	/**
	 * Retrieves the list of appointments for the given patient that have a date
	 * equal to the current day,
	 * ordered by date and time in ascending order.
	 * 
	 * @param patient the patient for whom to retrieve the appointments
	 * @return the list of appointments for the patient with a date equal to today,
	 *         ordered by date and time
	 */
	List<Appointment> getTodaysAppointmentList(Patient patient);

	/**
	 * Retrieves the list of appointments for the given physiotherapist that have a
	 * date equal to the current day,
	 * ordered by date and time in ascending order.
	 * 
	 * @param physiotherapist the physiotherapist for whom to retrieve the
	 *                        appointments
	 * @return the list of appointments for the physiotherapist with a date equal to
	 *         today, ordered by date and time
	 */
	List<Appointment> getTodaysAppointmentList(Physiotherapist physiotherapist);

	/**
	 * Retrieves a list of upcoming appointments for a specific patient.
	 * 
	 * @param patient The patient for whom to retrieve the upcoming appointments.
	 * @return A list of upcoming appointments for the specified patient.
	 */
	List<Appointment> getUpcomingAppointmentsForPatient(Patient patient);

	/**
	 * Retrieves a list of past appointments for a specific patient.
	 * 
	 * @param patient The patient for whom to retrieve the past appointments.
	 * @return A list of past appointments for the specified patient.
	 */
	List<Appointment> getPastAppoinmentsForPatient(Patient patient);

	/**
	 * Saves all of an appointment list.
	 * 
	 * @param appointments The appointment list to be saved.
	 */
	void saveAll(List<Appointment> appointments);

	/**
	 * 
	 * Retrieves all appointments for a specific patient.
	 * 
	 * @param patient The patient for whom to retrieve the appointments.
	 * @return A list of appointments belonging to the patient.
	 */
	List<Appointment> getAll(Patient patient);

	/**
	 * 
	 * Generates appointments for a specific physiotherapist on a given date, within
	 * a specified time range.
	 * 
	 * @param physiotherapist The physiotherapist for whom to generate appointments.
	 * @param date            The date on which the appointments will be generated.
	 * @param startTime       The start time of the appointment range.
	 * @param endTime         The end time of the appointment range.
	 * @return A list of generated appointments for the physiotherapist on the
	 *         specified date and time range.
	 */
	List<Appointment> generateAppointments(Physiotherapist physiotherapist, LocalDate date, LocalTime startTime,
			LocalTime endTime);

	/**
	 * Retrieves a list of upcoming appointments for a specific physiotherapist.
	 *
	 * @param physiotherapist The physiotherapist for whom to retrieve the
	 *                        appointments.
	 * @return A list of upcoming appointments for the specified physiotherapist.
	 */
    List<Appointment> getUpcomingAppointmentsForPhysiotherapist(Physiotherapist physiotherapist);

}
