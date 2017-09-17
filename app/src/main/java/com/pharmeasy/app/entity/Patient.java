package com.pharmeasy.app.entity;

import com.pharmeasy.auth.core.User;

import java.io.Serializable;
import java.util.Map;

public class Patient implements User {

    private Long id;
    private String name;
    private int age;
    private String username;
    private String address;

    // for prototype app, we are facilitating quick search by unique code(s).
    // for production app, we may need different criterias to look up a MedicalRecord or Prescription
    private Map<String, MedicalRecord> medicalRecords;
    private Map<String, Prescription> prescriptions;

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Serializable getId() {
        return id;
    }

    public String getType() {
        return getClass().getName();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return username != null ? username.equals(patient.username) : patient.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
