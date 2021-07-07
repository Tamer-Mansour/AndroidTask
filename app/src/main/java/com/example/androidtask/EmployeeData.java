package com.example.androidtask;

import android.widget.DatePicker;

public class EmployeeData {
    private String name;
    private String salary;
    private Boolean active;
    private DatePicker date;
    enum Gender{
        Male,Female
    }

    public EmployeeData(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public EmployeeData(Boolean active, DatePicker date) {
        this.active = active;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
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
