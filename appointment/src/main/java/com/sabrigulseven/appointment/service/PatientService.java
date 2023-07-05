package com.sabrigulseven.appointment.service;

import java.util.List;

import com.sabrigulseven.appointment.model.Patient;

public interface PatientService {

    /**
     * 
     * Saves a patient in the system.
     * 
     * @param patient The patient to be saved.
     * @return The saved patient.
     */
    Patient save(Patient patient);

    /**
     * 
     * Retrieves a patient by their ID.
     * 
     * @param id The ID of the patient.
     * @return The patient with the specified ID, or null if not found.
     */
    Patient getPatientById(long id);

    /**
     * 
     * Retrieves a patient by their identity number.
     * 
     * @param identityNumber The identity number of the patient.
     * @return The patient with the specified identity number, or null if not found.
     */
    Patient getPatientByIdentityNumber(long identityNumber);

    /**
     * 
     * Retrieves all patients in the system.
     * 
     * @return A list of all patients.
     */
    List<Patient> findAll();

    /**
     * 
     * Deletes a patient with the specified ID.
     * 
     * @param id The ID of the patient to be deleted.
     */
    void deleteById(Long id);

    /**
     * 
     * Deletes a patient from the system.
     * 
     * @param patient The patient to be deleted.
     */
    void delete(Patient patient);

    /**
     * 
     * Updates a patient from the system.
     * 
     * @param updatedPatient The patient to be updated.
     */
    void update(Patient updatedPatient);
}
