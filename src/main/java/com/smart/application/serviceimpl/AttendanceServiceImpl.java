package com.smart.application.serviceimpl;

import com.smart.application.dto.EmployeeAttendanceDTO;
import com.smart.application.models.Attendance;
import com.smart.application.models.Employee;
import com.smart.application.repository.AttendanceRepository;
import com.smart.application.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Optional<List<EmployeeAttendanceDTO>> getAttendanceSummary() {
        List<Attendance> allAttendances = attendanceRepository.findAll();
        Map<String, EmployeeAttendanceDTO> attendanceMap = new HashMap<>();

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        for (Attendance attendance : allAttendances) {
            String employeeName = attendance.getEmployeeName();
            EmployeeAttendanceDTO dto = attendanceMap.getOrDefault(employeeName, new EmployeeAttendanceDTO(employeeName, 0, 0));

            if (attendance.getMonth() == currentMonth && attendance.getYear() == currentYear) {
                dto.setMonthlyDaysPresent(dto.getMonthlyDaysPresent() + 1);
            }

            if (attendance.getYear() == currentYear) {
                dto.setYearlyDaysPresent(dto.getYearlyDaysPresent() + 1);
            }

            attendanceMap.put(employeeName, dto);
        }

        List<EmployeeAttendanceDTO> summary = attendanceMap.values().stream().collect(Collectors.toList());
        return Optional.ofNullable(summary);
    }

    @Override
    public Attendance markAttendance(Attendance attendance) {
        Employee employee = attendance.getEmployee();

        if (employee != null) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();

            Optional<Attendance> existingAttendanceOptional = attendanceRepository.findByEmployeeIdAndDayAndMonthAndYear(employee.getId(), currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());

            if (existingAttendanceOptional.isPresent()) {
                Attendance existingAttendance = existingAttendanceOptional.get();
                existingAttendance.setTimingOutHour(currentTime.getHour());
                existingAttendance.setTimingOutMinute(currentTime.getMinute());
                return attendanceRepository.save(existingAttendance);
            } else {
                attendance.setDay(currentDate.getDayOfMonth());
                attendance.setMonth(currentDate.getMonthValue());
                attendance.setYear(currentDate.getYear());
                attendance.setTimingInHour(currentTime.getHour());
                attendance.setTimingInMinute(currentTime.getMinute());
                attendance.setTimingOutHour(0);
                attendance.setTimingOutMinute(0);
                return attendanceRepository.save(attendance);
            }
        } else {
            throw new RuntimeException("Employee information not provided.");
        }
    }

    @Override
    public List<Attendance> getEmployeeAttendanceByName(String employeeName) {
        return attendanceRepository.findByEmployeeName(employeeName);
    }

    @Override
    public List<Attendance> getEmployeeAttendanceByEmployeeId(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    @Override
    public void deleteAttendance(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }

    @Override
    public int calculateMonthlyAttendance(String employeeName, int month, int year) {
        List<Attendance> attendances = attendanceRepository.findMonthlyAttendanceByEmployeeName(employeeName, month, year);
        return (int) attendances.stream().filter(a -> a.getTimingInHour() != 0 || a.getTimingOutHour() != 0).count();
    }

    @Override
    public int calculateYearlyAttendance(String employeeName, int year) {
        List<Attendance> attendances = attendanceRepository.findYearlyAttendanceByEmployeeName(employeeName, year);
        return (int) attendances.stream().filter(a -> a.getTimingInHour() != 0 || a.getTimingOutHour() != 0).count();
    }
}
