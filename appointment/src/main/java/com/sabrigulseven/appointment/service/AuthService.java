package com.sabrigulseven.appointment.service;

import com.sabrigulseven.appointment.model.User;

/**
 * Service interface for authentication and authorization.
 */
public interface AuthService {

    /**
     * Logs in a user with the specified identity number and password.
     *
     * @param identityNumber The identity number of the user.
     * @param password The password of the user.
     * @return The logged-in user, or null if authentication fails.
     */
    User loginUser(long identityNumber, String password);
}