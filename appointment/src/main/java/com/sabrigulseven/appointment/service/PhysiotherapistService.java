package com.sabrigulseven.appointment.service;

import java.util.List;

import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;

public interface PhysiotherapistService {

    /**
     * Saves a physiotherapist in the system.
     * 
     * @param physiotherapist The physiotherapist to be saved.
     * @return The saved physiotherapist.
     */
    Physiotherapist save(Physiotherapist physiotherapist);

    /**
     * Retrieves a physiotherapist by their ID.
     * 
     * @param id The ID of the physiotherapist.
     * @return The physiotherapist with the specified ID, or null if not found.
     */
    Physiotherapist getPhysiotherapistById(long id);

    /**
     * 
     * Retrieves all physiotherapists in the system.
     * 
     * @return A list of all physiotherapists.
     */
    List<Physiotherapist> getAll();

    /**
     * 
     * Deletes a physiotherapist from the system.
     * 
     * @param physiotherapist The physiotherapist to be deleted.
     */
    void delete(Physiotherapist physiotherapist);

    /**
     * 
     * Retrieves the list of patients associated with a physiotherapist.
     * 
     * @param physiotherapist The physiotherapist.
     * @return A list of patients associated with the physiotherapist.
     */
    List<Patient> getPatientsOfPhysiotherapist(Physiotherapist physiotherapist);

    /**
     * 
     * Updates the details of a physiotherapist in the system.
     * 
     * @param updatedPhysiotherapist The updated physiotherapist object.
     */
    void updatePhysiotherapist(Physiotherapist updatedPhysiotherapist);

    /**
     * 
     * Deletes a physiotherapist by their unique ID.
     * 
     * @param id The ID of the physiotherapist to be deleted.
     */
    void deleteById(Long id);

}
