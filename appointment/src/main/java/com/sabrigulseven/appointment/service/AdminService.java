package com.sabrigulseven.appointment.service;

import com.sabrigulseven.appointment.model.Admin;
/**
Service interface for managing Admins in the system.
*/
public interface AdminService {

    /**
     * Saves an admin in the system.
     *
     * @param admin The admin to be saved.
     * @return The saved admin.
     */
    Admin save(Admin admin);

    /**
     * Retrieves an admin by their ID.
     *
     * @param id The ID of the admin.
     * @return The admin with the specified ID, or null if not found.
     */
    Admin getAdminById(long id);

    /**
     * Retrieves an admin by their identity number.
     *
     * @param identityNumber The identity number of the admin.
     * @return The admin with the specified identity number, or null if not found.
     */
    Admin getAdminByIdentityNumber(long identityNumber);

}