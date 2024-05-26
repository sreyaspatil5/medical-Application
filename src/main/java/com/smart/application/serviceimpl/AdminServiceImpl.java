package com.smart.application.serviceimpl;

import com.smart.application.models.Admin;
import com.smart.application.repository.AdminRepository;
import com.smart.application.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin createAdmin(String username, String password) {
        // Check the number of existing rows in the admin table
        long existingAdminCount = adminRepository.count();
        if (existingAdminCount >= 1) {
            // Maximum capacity reached, output a message indicating that admin already exists
            System.out.println("Admin cannot be created. Maximum capacity reached.");
            return null;
        }

        // Create admin with the provided username and password
        Admin admin = new Admin(username, password);
        adminRepository.save(admin);

        return admin;
    }
}
