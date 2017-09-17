package com.pharmeasy.app.repo;

import com.pharmeasy.app.entity.MedicalRecord;
import com.pharmeasy.auth.core.Resource;

import java.util.List;

public interface MedicalRecordRepo {

    List<MedicalRecord> getMedicalRecords(String username);

    List<MedicalRecord> getMedicalRecords(Long userId);

    // avoids fetching the entire Medical Record
    Resource getMedicalRecAsResource(String medRecCode);

    MedicalRecord getMedicalRecord(String medRecCode);


    // other CRUD methods


}
