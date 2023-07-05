package com.sabrigulseven.appointment.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(indexes = @Index(name = "date_index",columnList = "date"))

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appointmentId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "physiotherapist_id", referencedColumnName = "id")
    private Physiotherapist physiotherapist;
    
    private LocalDate date;

    private LocalTime time;
    
    private Boolean attendance;
    
    public Appointment() {
    }

    public Appointment(long appointmentId, Patient patient, Physiotherapist physiotherapist, LocalDate date, LocalTime time, Boolean attendance) {
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
