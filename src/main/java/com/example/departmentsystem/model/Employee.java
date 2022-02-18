package com.example.departmentsystem.model;

import java.util.Date;
import java.util.Objects;

public class Employee {

    private int id;
    private String email;
    private int department_id;
    private int salary;
    private Date hireDate;

    public Employee() {
    }

    public Employee(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(String email) {
        this.email = email;
    }

    public Employee(int id, String email, int department_id, int salary, Date hireDate) {
        this.id = id;
        this.email = email;
        this.department_id = department_id;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(department_id, employee.department_id) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department_id, salary, hireDate, email);
    }
}
