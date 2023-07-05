package com.sabrigulseven.appointment.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.repository.AppointmentRepository;
import com.sabrigulseven.appointment.service.AppointmentService;
import com.sabrigulseven.appointment.util.ScheduleConstants;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAppointmentList(Long physiotherapistId, LocalDate startDate, LocalDate endDate) {
        Specification<Appointment> spec = Specification.where(null);
        if (physiotherapistId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("physiotherapist").get("id"), physiotherapistId));
        }
        if (startDate != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("date"),
                    startDate));
        }
        if (endDate != null) {
            spec = spec.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDate));
        }
        Sort sort = Sort.by("date").ascending().and(Sort.by("time").ascending());
        return appointmentRepository.findAll(spec, sort);
    }

    @Override
    public List<Appointment> getAvailableAppointmentsForPhysiotherapist(Physiotherapist physiotherapist) {
        return appointmentRepository.findByPhysiotherapistAndDateAfterAndPatientIsNullOrderByDateAscTimeAsc(physiotherapist,
                LocalDate.now());
    }

    @Override
    public Optional<Appointment> getById(long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Patient patient, Physiotherapist physiotherapist, LocalDate date) {
        return appointmentRepository.findAppointmentByPatientAndPhysiotherapistAndDate(patient, physiotherapist, date);
    }

    @Override
    public List<Appointment> getTodaysAppointmentList(Patient patient) {
        return appointmentRepository.findByPatientAndDateOrderByDateAscTimeAsc(patient, LocalDate.now());
    }

    @Override
    public List<Appointment> getUpcomingAppointmentsForPatient(Patient patient) {
        return appointmentRepository.findByPatientAndDateAfterOrderByDateAscTimeAsc(patient, LocalDate.now());
    }

    @Override
    public List<Appointment> getPastAppoinmentsForPatient(Patient patient) {
        return appointmentRepository.findByPatientAndDateBeforeOrderByDateDesc(patient, LocalDate.now());
    }

    @Override
    public List<Appointment> getTodaysAppointmentList(Physiotherapist physiotherapist) {
        return appointmentRepository.findByPhysiotherapistAndDateOrderByDateAscTimeAsc(physiotherapist,
                LocalDate.now());
    }

    @Override
    public void saveAll(List<Appointment> appointments) {
        appointmentRepository.saveAll(appointments);
    }

    @Override
    public List<Appointment> getAll(Patient patient) {
        return appointmentRepository.findByPatientOrderByDateAscTimeAsc(patient);
    }

    public List<Appointment> generateAppointments(Physiotherapist physiotherapist, LocalDate date, LocalTime startTime,
            LocalTime endTime) {
        Set<LocalTime> existingAppointmentTimes = appointmentRepository
            .findByPhysiotherapistAndDateAndTimeBetweenOrderByDateAscTimeAsc(physiotherapist, date, startTime, endTime)
            .stream()
            .map(Appointment::getTime)
            .collect(Collectors.toSet());

        LocalTime appointmentTime = startTime;
        List<Appointment> appointments = new ArrayList<>();
        while (appointmentTime.isBefore(endTime)) {
            if (appointmentTime.getHour() != 12 && !existingAppointmentTimes.contains(appointmentTime)) {
                Appointment appointment = new Appointment(0, null, physiotherapist, date, appointmentTime, false);
                appointments.add(appointment);
            }
            appointmentTime = appointmentTime.plusMinutes(ScheduleConstants.APPOINTMENT_DURATION);
        }
        return appointments;
    }

    @Override
    public List<Appointment> getUpcomingAppointmentsForPhysiotherapist(Physiotherapist physiotherapist) {
        return appointmentRepository.findByPhysiotherapistAndDateAfterOrderByDateAscTimeAsc(physiotherapist, LocalDate.now());
    }

}
