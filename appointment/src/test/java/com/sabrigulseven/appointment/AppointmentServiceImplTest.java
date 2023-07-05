package com.sabrigulseven.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.sabrigulseven.appointment.model.Appointment;
import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.repository.AppointmentRepository;
import com.sabrigulseven.appointment.service.impl.AppointmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test @SuppressWarnings("unchecked")
    public void testGetAppointmentList_WithFilters_ShouldReturnFilteredAppointments() {
        // Arrange
        Long physiotherapistId = 1L;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findAll(any(Specification.class), any(Sort.class))).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getAppointmentList(physiotherapistId, startDate, endDate);

        // Assert
        assertEquals(expectedAppointments, result);
        verify(appointmentRepository).findAll(any(Specification.class), any(Sort.class));
    }

    @Test
    public void testGetAvailableAppointmentsForPhysiotherapist_ShouldReturnAvailableAppointments() {
        // Arrange
        Physiotherapist physiotherapist = new Physiotherapist();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPhysiotherapistAndDateAfterAndPatientIsNullOrderByDateAscTimeAsc(
                eq(physiotherapist), any(LocalDate.class))).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getAvailableAppointmentsForPhysiotherapist(physiotherapist);

        // Assert
        assertEquals(expectedAppointments, result);
        verify(appointmentRepository).findByPhysiotherapistAndDateAfterAndPatientIsNullOrderByDateAscTimeAsc(
                eq(physiotherapist), any(LocalDate.class));
    }

    @Test
    public void testGetById_ValidAppointmentId_ShouldReturnOptionalOfAppointment() {
        // Arrange
        long appointmentId = 1L;
        Appointment expectedAppointment = new Appointment();
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(expectedAppointment));

        // Act
        Optional<Appointment> result = appointmentService.getById(appointmentId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedAppointment, result.get());
        verify(appointmentRepository).findById(appointmentId);
    }

    @Test
    public void testGetAppointment_ValidPatientPhysiotherapistAndDate_ShouldReturnAppointment() {
        // Arrange
        Patient patient = new Patient();
        Physiotherapist physiotherapist = new Physiotherapist();
        LocalDate date = LocalDate.now();
        Appointment expectedAppointment = new Appointment();
        when(appointmentRepository.findAppointmentByPatientAndPhysiotherapistAndDate(
                eq(patient), eq(physiotherapist), eq(date))).thenReturn(expectedAppointment);

        // Act
        Appointment result = appointmentService.getAppointment(patient, physiotherapist, date);

        // Assert
        assertEquals(expectedAppointment, result);
        verify(appointmentRepository).findAppointmentByPatientAndPhysiotherapistAndDate(
                eq(patient), eq(physiotherapist), eq(date));
    }

    @Test
    public void testGetTodaysAppointmentList_ValidPatient_ShouldReturnTodaysAppointments() {
        // Arrange
        Patient patient = new Patient();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPatientAndDateOrderByDateAscTimeAsc(
                eq(patient), any(LocalDate.class))).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getTodaysAppointmentList(patient);

        // Assert
        assertEquals(expectedAppointments, result);
        verify(appointmentRepository).findByPatientAndDateOrderByDateAscTimeAsc(
                eq(patient), any(LocalDate.class));
    }

    @Test
    public void testGetUpcomingAppointmentsForPatient_ValidPatient_ShouldReturnUpcomingAppointments() {
        // Arrange
        Patient patient = new Patient();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPatientAndDateAfterOrderByDateAscTimeAsc(
                eq(patient), any(LocalDate.class))).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getUpcomingAppointmentsForPatient(patient);

        // Assert
        assertEquals(expectedAppointments, result);
        verify(appointmentRepository).findByPatientAndDateAfterOrderByDateAscTimeAsc(
                eq(patient), any(LocalDate.class));
    }

    @Test
    public void testGetPastAppoinmentsForPatient_ValidPatient_ShouldReturnPastAppointments() {
        // Arrange
        Patient patient = new Patient();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPatientAndDateBeforeOrderByDateDesc(
                eq(patient), any(LocalDate.class))).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getPastAppoinmentsForPatient(patient);

        // Assert
        assertEquals(expectedAppointments, result);
        verify(appointmentRepository).findByPatientAndDateBeforeOrderByDateDesc(
                eq(patient), any(LocalDate.class));
    }

    @Test
    public void testGetTodaysAppointmentList_ValidPhysiotherapist_ShouldReturnTodaysAppointments() {
        // Arrange
        Physiotherapist physiotherapist = new Physiotherapist();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPhysiotherapistAndDateOrderByDateAscTimeAsc(
                eq(physiotherapist), any(LocalDate.class))).thenReturn(expectedAppointments);

        // Act
        List<Appointment> result = appointmentService.getTodaysAppointmentList(physiotherapist);

        // Assert
        assertEquals(expectedAppointments, result);
        verify(appointmentRepository).findByPhysiotherapistAndDateOrderByDateAscTimeAsc(
                eq(physiotherapist), any(LocalDate.class));
    }

    @Test
    public void testSaveAll() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentService.saveAll(appointments);

        verify(appointmentRepository, times(1)).saveAll(appointments);
    }

    @Test
    public void testGetAll() {
        Patient patient = new Patient();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPatientOrderByDateAscTimeAsc(patient)).thenReturn(expectedAppointments);

        List<Appointment> result = appointmentService.getAll(patient);

        assertEquals(expectedAppointments, result);
        verify(appointmentRepository, times(1)).findByPatientOrderByDateAscTimeAsc(patient);
    }

    @Test
    public void testGenerateAppointments() {
        Physiotherapist physiotherapist = new Physiotherapist();
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(16, 0);

        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentService.generateAppointments(
                physiotherapist, date, startTime, endTime)).thenReturn(expectedAppointments);

        List<Appointment> result = appointmentRepository
                .findByPhysiotherapistAndDateAndTimeBetweenOrderByDateAscTimeAsc(physiotherapist, date, startTime,
                        endTime);
        assertEquals(expectedAppointments, result);

        verify(appointmentRepository, times(1)).findByPhysiotherapistAndDateAndTimeBetweenOrderByDateAscTimeAsc(
                physiotherapist, date, startTime, endTime);
    }

    @Test
    public void testGetUpcomingAppointmentsForPhysiotherapist() {
        Physiotherapist physiotherapist = new Physiotherapist();
        List<Appointment> expectedAppointments = new ArrayList<>();
        when(appointmentRepository.findByPhysiotherapistAndDateAfterOrderByDateAscTimeAsc(
                physiotherapist, LocalDate.now())).thenReturn(expectedAppointments);

        List<Appointment> result = appointmentService.getUpcomingAppointmentsForPhysiotherapist(physiotherapist);

        assertEquals(expectedAppointments, result);
        verify(appointmentRepository, times(1)).findByPhysiotherapistAndDateAfterOrderByDateAscTimeAsc(
                physiotherapist, LocalDate.now());
    }
}
