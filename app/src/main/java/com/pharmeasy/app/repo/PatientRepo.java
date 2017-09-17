package com.pharmeasy.app.repo;

import com.pharmeasy.app.entity.Patient;

public interface PatientRepo {

    Patient getPatient(String username);

    Patient getPatient(Long id);

    // other CRUD methods

}
