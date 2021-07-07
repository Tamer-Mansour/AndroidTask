package com.example.androidtask;

import android.widget.DatePicker;

public class EmployeeData {
    private String name;
    private double salary;
    private Boolean active;
    private DatePicker date;
    enum Gender{
        Male,Female
    }

    public EmployeeData(String name, double salary, Boolean active, DatePicker date) {
        this.name = name;
        this.salary = salary;
        this.active = active;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }
}
