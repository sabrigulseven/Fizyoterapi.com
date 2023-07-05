package com.sabrigulseven.appointment.service;

/**
 * Service interface for sending email notifications.
 */
public interface EmailSenderService {

    /**
     * Sends an email with a treatment attachment to the specified email address.
     *
     * @param email The email address of the recipient.
     * @param pathName The path to the treatment file to be attached.
     */
    void sendTreatmentMail(String email, String pathName);
}