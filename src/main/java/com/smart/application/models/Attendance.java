package com.smart.application.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String employeeName;
    private int day;
    private int month;
    private int year;
    private int timingInHour;
    private int timingInMinute;
    private int timingOutHour;
    private int timingOutMinute;

    public Attendance(String employeeName, Long id, Employee employee, int day, int month, int year, int timingInHour, int timingInMinute, int timingOutHour, int timingOutMinute) {
        this.id = id;
        this.employee = employee;
        this.day = day;
        this.month = month;
        this.year = year;
        this.timingInHour = timingInHour;
        this.timingInMinute = timingInMinute;
        this.timingOutHour = timingOutHour;
        this.timingOutMinute = timingOutMinute;
        this.employeeName = employeeName;
    }

    public Attendance() {
        super();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTimingInHour() {
        return timingInHour;
    }

    public void setTimingInHour(int timingInHour) {
        this.timingInHour = timingInHour;
    }

    public int getTimingInMinute() {
        return timingInMinute;
    }

    public void setTimingInMinute(int timingInMinute) {
        this.timingInMinute = timingInMinute;
    }

    public int getTimingOutHour() {
        return timingOutHour;
    }

    public void setTimingOutHour(int timingOutHour) {
        this.timingOutHour = timingOutHour;
    }

    public int getTimingOutMinute() {
        return timingOutMinute;
    }

    public void setTimingOutMinute(int timingOutMinute) {
        this.timingOutMinute = timingOutMinute;
    }
}
