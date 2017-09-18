package com.pharmeasy.app.repo;

import com.pharmeasy.app.entity.Patient;

public interface PatientRepo {

    Patient getPatient(String username);

    Patient getPatient(Long id);

    void savePatient(Patient patient);

    // other CRUD methods

}
