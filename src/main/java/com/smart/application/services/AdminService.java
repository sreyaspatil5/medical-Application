package com.smart.application.services;

import com.smart.application.models.Admin;

public interface AdminService {
    Admin getAdminByUsername(String username);

    Admin createAdmin(String username, String password);
}

