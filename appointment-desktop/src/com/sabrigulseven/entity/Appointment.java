package com.sabrigulseven.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
	
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient + ", physiotherapist="
				+ physiotherapist + ", date=" + date + ", time=" + time + ", attendance=" + attendance + "]";
	}

	private long appointmentId;

    private Patient patient;

    private Physiotherapist physiotherapist;
    
    private LocalDate date;

    private LocalTime time;
    
    private Boolean attendance;
    
    public Appointment() {
		
	}

	public Appointment(long appointmentId, Patient patient, Physiotherapist physiotherapist, LocalDate date,
			LocalTime time, Boolean attendance) {
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.physiotherapist = physiotherapist;
		this.date = date;
		this.time = time;
		this.attendance = attendance;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Physiotherapist getPhysiotherapist() {
		return physiotherapist;
	}

	public void setPhysiotherapist(Physiotherapist physiotherapist) {
		this.physiotherapist = physiotherapist;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Boolean getAttendance() {
		return attendance;
	}

	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}
    
    
}
