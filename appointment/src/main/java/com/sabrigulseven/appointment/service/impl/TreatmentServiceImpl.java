package com.sabrigulseven.appointment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sabrigulseven.appointment.model.Treatment;
import com.sabrigulseven.appointment.repository.TreatmentRepository;
import com.sabrigulseven.appointment.service.TreatmentService;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> getAll() {
        return treatmentRepository.findAll();

    }

    @Override
    public Treatment getById(Long id) {
        Optional<Treatment> optionalTreatment = treatmentRepository.findById(id);
        if (optionalTreatment.isPresent())
            return optionalTreatment.get();
        return null;
    }

    @Override
    public void delete(Treatment treatment) {
        treatmentRepository.delete(treatment);
    }

    @Override
    public Treatment save(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }
    
}
