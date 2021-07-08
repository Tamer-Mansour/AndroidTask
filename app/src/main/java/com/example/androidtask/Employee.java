package com.example.androidtask;

import android.widget.DatePicker;

import java.time.LocalDate;
import java.util.Date;

public class Employee {
    private String name;
    private double salary;
    private boolean active;
    private LocalDate date;
    private Gender gender;
    enum Gender{
        Male,Female;

    }

    public Employee(String name, double salary, Boolean active, LocalDate date, Gender gender) {
        this.name = name;
        this.salary = salary;
        this.active = active;
        this.date = date;
        this.gender = gender;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
