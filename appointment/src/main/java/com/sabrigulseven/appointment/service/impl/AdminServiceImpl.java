package com.sabrigulseven.appointment.service.impl;

import org.springframework.stereotype.Service;

import com.sabrigulseven.appointment.model.Admin;
import com.sabrigulseven.appointment.model.User;
import com.sabrigulseven.appointment.repository.AdminRepository;
import com.sabrigulseven.appointment.service.AdminService;
import com.sabrigulseven.appointment.service.AuthService;

@Service
public class AdminServiceImpl implements AdminService, AuthService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public User loginUser(long identityNumber, String password) {
        return adminRepository.findByIdentityNumberAndPassword(identityNumber, password);
    }

    @Override
    public Admin save(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Admin getAdminById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdminById'");
    }

    @Override
    public Admin getAdminByIdentityNumber(long identityNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdminByIdentityNumber'");
    }
    
}
