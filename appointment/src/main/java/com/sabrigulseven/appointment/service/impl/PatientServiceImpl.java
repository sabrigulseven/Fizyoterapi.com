package com.sabrigulseven.appointment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.User;
import com.sabrigulseven.appointment.repository.PatientRepository;
import com.sabrigulseven.appointment.service.AuthService;
import com.sabrigulseven.appointment.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService, AuthService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public User loginUser(long identityNumber, String password) {
        return patientRepository.findByIdentityNumberAndPassword(identityNumber, password);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent())
            return optionalPatient.get();
        return null;
    }

    // @Override
    // public Optional<Patient> getPatientById(long id) {
    //     return patientRepository.findById(id);
    // }    

    @Override
    public Patient getPatientByIdentityNumber(long identityNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPatientByIdentityNumber'");
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Patient patient = getPatientById(id);
        if (patient != null) {
            delete(patient);
        }
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public void update(Patient updatedPatient) {
        Patient patient = getPatientById(updatedPatient.getId());
        if (patient == null) {
            throw new IllegalArgumentException("Invalid patient Id:" + updatedPatient.getId());
        }
        patient.setIdentityNumber(updatedPatient.getIdentityNumber());
        patient.setName(updatedPatient.getName());
        patient.setEmailAddress(updatedPatient.getEmailAddress());
        save(patient);
    }

}
