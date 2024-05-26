package com.smart.application.controller;

import com.smart.application.dto.EmployeeAttendanceDTO;
import com.smart.application.models.Attendance;
import com.smart.application.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/mark")
    public ResponseEntity<Attendance> markAttendance(@RequestBody Attendance attendance) {
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        int day = currentTime.getDayOfMonth();
        int month = currentTime.getMonthValue();
        int year = currentTime.getYear();

        attendance.setTimingInHour(hour);
        attendance.setTimingInMinute(minute);
        attendance.setDay(day);
        attendance.setMonth(month);
        attendance.setYear(year);

        Attendance markedAttendance = attendanceService.markAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(markedAttendance);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<EmployeeAttendanceDTO>> getAttendanceSummary() {
        Optional<List<EmployeeAttendanceDTO>> summaryOptional = attendanceService.getAttendanceSummary();

        if (summaryOptional.isPresent()) {
            return ResponseEntity.ok(summaryOptional.get());
        } else {
            // Handle case where no summary is found, return appropriate response
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/name/{name}")
    public ResponseEntity<List<Attendance>> getEmployeeAttendanceByName(@PathVariable String name) {
        List<Attendance> attendanceList = attendanceService.getEmployeeAttendanceByName(name);
        return ResponseEntity.ok(attendanceList);
    }

    @GetMapping("/employee/id/{id}")
    public ResponseEntity<List<Attendance>> getEmployeeAttendanceByEmployeeId(@PathVariable Long id) {
        List<Attendance> attendanceList = attendanceService.getEmployeeAttendanceByEmployeeId(id);
        return ResponseEntity.ok(attendanceList);
    }

    @GetMapping("/monthly/{name}/{month}/{year}")
    public ResponseEntity<Integer> getMonthlyAttendance(@PathVariable String name, @PathVariable int month, @PathVariable int year) {
        int totalAttendance = attendanceService.calculateMonthlyAttendance(name, month, year);
        return ResponseEntity.ok(totalAttendance);
    }

    @GetMapping("/yearly/{name}/{year}")
    public ResponseEntity<Integer> getYearlyAttendance(@PathVariable String name, @PathVariable int year) {
        int totalAttendance = attendanceService.calculateYearlyAttendance(name, year);
        return ResponseEntity.ok(totalAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance with ID " + id + " deleted successfully");
    }
}
