package com.example.departmentsystem.model;


import java.util.Objects;

public class Department {

    private int id;

    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(int id) {
        this.id = id;
    }

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department department = (Department) o;
        return Objects.equals(id, department.id) &&
                Objects.equals(name, department.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
