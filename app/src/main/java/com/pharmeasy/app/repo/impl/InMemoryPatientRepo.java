package com.pharmeasy.app.repo.impl;

import com.pharmeasy.app.entity.Patient;
import com.pharmeasy.app.repo.PatientRepo;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPatientRepo implements PatientRepo {

    Map<String, Patient> patientMap;

    public InMemoryPatientRepo() {
        patientMap = new HashMap<>();
    }

    @Override
    public Patient getPatient(String username) {
        return patientMap.get(username);
    }

    @Override
    public Patient getPatient(Long id) {
        throw new UnsupportedOperationException("yet to be implemented");
    }

    @Override
    public void savePatient(Patient patient) {
        patientMap.put(patient.getUsername(), patient);
    }
}
