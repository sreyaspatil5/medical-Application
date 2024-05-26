package com.smart.application.repository;

import com.smart.application.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeIdAndDayAndMonthAndYear(Long employeeId, int day, int month, int year);
    List<Attendance> findByEmployeeName(String employeeName);
    List<Attendance> findByEmployeeId(Long employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.employeeName = ?1 AND a.month = ?2 AND a.year = ?3")
    List<Attendance> findMonthlyAttendanceByEmployeeName(String employeeName, int month, int year);

    @Query("SELECT a FROM Attendance a WHERE a.employeeName = ?1 AND a.year = ?2")
    List<Attendance> findYearlyAttendanceByEmployeeName(String employeeName, int year);
}
