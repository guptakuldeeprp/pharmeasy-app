package com.pharmeasy.app.repo;

import com.pharmeasy.app.entity.MedicalRecord;
import com.pharmeasy.app.entity.Prescription;
import com.pharmeasy.auth.core.Resource;

import java.util.List;

public interface PrescriptionRepo {

    List<Prescription> getPrescriptions(String username);

    List<Prescription> getPrescriptions(Long userId);

    Prescription getPrescription(String prescriptionCode);

    // avoids fetching the entire Prescription
    Resource getPrescritionAsResource(String prescriptionCode);


    // other CRUD methods
}
