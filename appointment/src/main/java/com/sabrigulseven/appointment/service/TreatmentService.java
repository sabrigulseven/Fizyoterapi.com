package com.sabrigulseven.appointment.service;

import java.util.List;

import com.sabrigulseven.appointment.model.Treatment;

/**
 * Service interface for managing treatments.
 */
public interface TreatmentService {

    /**
     * Retrieves all treatments.
     *
     * @return A list of all treatments.
     */
    List<Treatment> getAll();

    /**
     * Retrieves a treatment by its ID.
     *
     * @param id The ID of the treatment.
     * @return The treatment with the specified ID, or null if not found.
     */
    Treatment getById(Long id);

    /**
     * Deletes a treatment from the system.
     *
     * @param treatment The treatment to be deleted.
     */
    void delete(Treatment treatment);

    /**
     * Saves a treatment in the system.
     *
     * @param treatment The treatment to be saved.
     * @return The saved treatment.
     */
    Treatment save(Treatment treatment);
}
