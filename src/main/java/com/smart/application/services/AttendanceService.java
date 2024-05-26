package com.smart.application.services;

import com.smart.application.dto.EmployeeAttendanceDTO;
import com.smart.application.models.Attendance;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    Attendance markAttendance(Attendance attendance);
    List<Attendance> getEmployeeAttendanceByName(String employeeName);
    List<Attendance> getEmployeeAttendanceByEmployeeId(Long employeeId);
    void deleteAttendance(Long attendanceId);
    int calculateMonthlyAttendance(String employeeName, int month, int year);
    int calculateYearlyAttendance(String employeeName, int year);
    Optional<List<EmployeeAttendanceDTO>> getAttendanceSummary();
}
