package com.smart.application.dto;

public class EmployeeAttendanceDTO {
    private String employeeName;
    private int monthlyDaysPresent;
    private int yearlyDaysPresent;

    public EmployeeAttendanceDTO(String employeeName, int monthlyDaysPresent, int yearlyDaysPresent) {
        this.employeeName = employeeName;
        this.monthlyDaysPresent = monthlyDaysPresent;
        this.yearlyDaysPresent = yearlyDaysPresent;
    }

    // Getters and setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getMonthlyDaysPresent() {
        return monthlyDaysPresent;
    }

    public void setMonthlyDaysPresent(int monthlyDaysPresent) {
        this.monthlyDaysPresent = monthlyDaysPresent;
    }

    public int getYearlyDaysPresent() {
        return yearlyDaysPresent;
    }

    public void setYearlyDaysPresent(int yearlyDaysPresent) {
        this.yearlyDaysPresent = yearlyDaysPresent;
    }
}
