package com.pharmeasy.app.repo.impl;

import com.pharmeasy.app.entity.MedicalRecord;
import com.pharmeasy.app.repo.MedicalRecordRepo;
import com.pharmeasy.auth.core.Resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryMedicalRecordRepo implements MedicalRecordRepo {

    private Map<String, List<MedicalRecord>> medRecMap;

    public InMemoryMedicalRecordRepo(Map<String, List<MedicalRecord>> medRecMap) {
        if (medRecMap == null) throw new NullPointerException("medRecMap is null");
        this.medRecMap = medRecMap;
    }

    public List<MedicalRecord> getMedicalRecords(String username) {
        return medRecMap.get(username);
    }

    public List<MedicalRecord> getMedicalRecords(Long userId) {
        throw new UnsupportedOperationException("yet to be implemented");
    }

    public Resource getMedicalRecAsResource(String medRecCode) {
        return getMedicalRecord(medRecCode);
    }

    public MedicalRecord getMedicalRecord(String medRecCode) {
        List<MedicalRecord> resultList = medRecMap
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(medRec -> medRec.getMedRecCode().equals(medRecCode))
                .collect(Collectors.toList());
        if (resultList.size() > 1)
            throw new IllegalStateException("Multiple medical records found with same code " + medRecCode);
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
