package com.smart.application.controller;


import com.smart.application.models.Admin;
import com.smart.application.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Object> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.createAdmin(admin.getUsername(), admin.getPassword());
        if (createdAdmin != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin cannot be created. Maximum capacity reached.");
        }
    }
}

