package com.pharmeasy.app.entity;

import com.pharmeasy.auth.core.Resource;

import java.util.Date;
import java.util.List;

public class Prescription implements Resource, Comparable<Prescription> {

    private Long id;
    private String prescriptionCode;
    private String description; // to be showed on UI
    private Date prescriptionDate;
    private List<String> prescriptionItems;
    private Patient patient;

    // more domain specific properties

    public Prescription() {

    }

    public Prescription(Long id, Patient patient, String prescriptionCode, String description) {
        this.id = id;
        this.patient = patient;
        this.prescriptionCode = prescriptionCode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public List<String> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(List<String> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescription that = (Prescription) o;

        return prescriptionCode != null ? prescriptionCode.equals(that.prescriptionCode) : that.prescriptionCode == null;
    }

    @Override
    public int hashCode() {
        return prescriptionCode != null ? prescriptionCode.hashCode() : 0;
    }

    public String getType() {
        return getClass().getName();
    }

    public int compareTo(Prescription o) {
        return prescriptionDate.compareTo(o.getPrescriptionDate());
    }
}
