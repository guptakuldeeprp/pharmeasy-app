package com.pharmeasy.app.entity;

import com.pharmeasy.auth.core.Resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MedicalRecord implements Resource, Comparable<MedicalRecord> {

    private Long id;
    private String medRecCode;
    private String description; // to be showed on UI
    private Date date;
    private List<String> notes; // Note can be a separate entity instead of string
    private List<String> testResults; // TestResult can be a separate entity instead of string
    private Patient patient;

    // more domain specific properties and their accessor methods

    public MedicalRecord() {

    }

    public MedicalRecord(Long id, Patient patient, String medRecCode, String description) {
        this.id = id;
        this.medRecCode = medRecCode;
        this.description = description;
        this.patient = patient;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedRecCode() {
        return medRecCode;
    }

    public void setMedRecCode(String medRecCode) {
        this.medRecCode = medRecCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<String> testResults) {
        this.testResults = testResults;
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

        MedicalRecord that = (MedicalRecord) o;

        return medRecCode != null ? medRecCode.equals(that.medRecCode) : that.medRecCode == null;
    }

    @Override
    public int hashCode() {
        return medRecCode != null ? medRecCode.hashCode() : 0;
    }

    public String getType() {
        return getClass().getName();
    }

    public int compareTo(MedicalRecord o) {
        return date.compareTo(o.getDate());
    }
}
