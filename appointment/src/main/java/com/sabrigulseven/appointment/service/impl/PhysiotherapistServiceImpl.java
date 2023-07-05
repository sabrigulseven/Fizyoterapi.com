package com.sabrigulseven.appointment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sabrigulseven.appointment.model.Patient;
import com.sabrigulseven.appointment.model.Physiotherapist;
import com.sabrigulseven.appointment.model.User;
import com.sabrigulseven.appointment.repository.PhysiotherapistRepository;
import com.sabrigulseven.appointment.service.AuthService;
import com.sabrigulseven.appointment.service.PhysiotherapistService;

@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService, AuthService{
    private final PhysiotherapistRepository physiotherapistRepository;

    public PhysiotherapistServiceImpl(PhysiotherapistRepository physiotherapistRepository) {
        this.physiotherapistRepository = physiotherapistRepository;
    }

    @Override
    public User loginUser(long identityNumber, String password) {
        return physiotherapistRepository.findByIdentityNumberAndPassword(identityNumber, password);
    }

    @Override
    public Physiotherapist save(Physiotherapist physiotherapist) {
        return physiotherapistRepository.save(physiotherapist);
    }

    @Override
    public Physiotherapist getPhysiotherapistById(long id) {
        Optional<Physiotherapist> optionalPhysiotherapist = physiotherapistRepository.findById(id);
        if(optionalPhysiotherapist.isPresent())
            return optionalPhysiotherapist.get();
        return null;
    }

    @Override
    public List<Physiotherapist> getAll() {
        return physiotherapistRepository.findAll();
    }

    @Override
    public void delete(Physiotherapist physiotherapist) {
        physiotherapistRepository.delete(physiotherapist);
    }

    @Override
    public List<Patient> getPatientsOfPhysiotherapist(Physiotherapist physiotherapist) {
        return physiotherapistRepository.findPatientsByPhysiotherapist(physiotherapist);
    }

    @Override
    public void updatePhysiotherapist(Physiotherapist updatedPhysiotherapist) {
        Physiotherapist physiotherapist = getPhysiotherapistById(updatedPhysiotherapist.getId());
		if(physiotherapist==null) {
			throw new IllegalArgumentException("Invalid physiotherapist Id:" + updatedPhysiotherapist.getId());
		}
        physiotherapist.setIdentityNumber(updatedPhysiotherapist.getIdentityNumber());
        physiotherapist.setName(updatedPhysiotherapist.getName());
        physiotherapist.setProfession(updatedPhysiotherapist.getProfession());
        save(physiotherapist);
    }

    @Override
    public void deleteById(Long id) {
        delete(getPhysiotherapistById(id));
    }
}
