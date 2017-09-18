package com.pharmeasy.app.repo.impl;

import com.pharmeasy.app.entity.Prescription;
import com.pharmeasy.app.repo.PrescriptionRepo;
import com.pharmeasy.auth.core.Resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryPrescriptionRepo implements PrescriptionRepo {

    private Map<String, List<Prescription>> prescriptionMap;

    public InMemoryPrescriptionRepo(Map<String, List<Prescription>> prescriptionMap) {
        this.prescriptionMap = prescriptionMap;
    }


    @Override
    public List<Prescription> getPrescriptions(String username) {
        return prescriptionMap.get(username);
    }

    @Override
    public List<Prescription> getPrescriptions(Long userId) {
        throw new UnsupportedOperationException("yet to be implemented");
    }

    @Override
    public Prescription getPrescription(String prescriptionCode) {
        List<Prescription> resultList = prescriptionMap
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(prescription -> prescription.getPrescriptionCode().equals(prescriptionCode))
                .collect(Collectors.toList());
        if (resultList.size() > 1)
            throw new IllegalStateException("Multiple prescription found with same code " + prescriptionCode);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public Resource getPrescritionAsResource(String prescriptionCode) {
        return  getPrescription(prescriptionCode);
    }
}
