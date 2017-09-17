package com.pharmeasy.app.service;

import com.pharmeasy.app.entity.MedicalRecord;
import com.pharmeasy.app.entity.Patient;
import com.pharmeasy.app.entity.Prescription;
import com.pharmeasy.app.repo.MedicalRecordRepo;
import com.pharmeasy.app.repo.PatientRepo;
import com.pharmeasy.app.repo.PrescriptionRepo;
import com.pharmeasy.auth.PermissionGrantingManager;
import com.pharmeasy.auth.UserContext;
import com.pharmeasy.auth.core.Permission;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.ex.AccessDeniedException;
import com.pharmeasy.auth.util.BasicPermissions;

import java.util.Collections;
import java.util.List;

// Acts as a facade. Avoids writing business logic in POJOs/DTOs.
public class PatientService {

    private PatientRepo patientRepo;
    private MedicalRecordRepo medicalRecordRepo;
    private PrescriptionRepo prescriptionRepo;
    private PermissionGrantingManager permissionGrantingManager;


    public PatientService(PatientRepo patientRepo, MedicalRecordRepo medicalRecordRepo, PrescriptionRepo prescriptionRepo) {
        this(patientRepo, medicalRecordRepo, prescriptionRepo, null);
    }

    // can use a dependency injection framework or a builder pattern to create this object
    public PatientService(PatientRepo patientRepo, MedicalRecordRepo medicalRecordRepo, PrescriptionRepo prescriptionRepo, PermissionGrantingManager permissionGrantingManager) {
        this.patientRepo = patientRepo;
        this.medicalRecordRepo = medicalRecordRepo;
        this.prescriptionRepo = prescriptionRepo;
        this.permissionGrantingManager = permissionGrantingManager;
    }


    public Patient getPatient(String username) {
        return patientRepo.getPatient(username);
    }

    public Patient getPatient(Long id) {
        return patientRepo.getPatient(id);
    }

    public MedicalRecord getMedicalRecord(Patient patient, String medicalRecCode) {
        checkPermission(medicalRecordRepo.getMedicalRecAsResource(medicalRecCode), BasicPermissions.READ);
        return medicalRecordRepo.getMedicalRecord(medicalRecCode);
    }

    public List<MedicalRecord> getMedicalRecords(Patient patient) {
        if (!UserContext.getUser().getUsername().equals(patient.getUsername()))
            throw new AccessDeniedException("Access to all medical records is permitted only to woner");
        return medicalRecordRepo.getMedicalRecords(patient.getUsername());
    }

    // used for read only view of medical records for display purposes
    public List<MedicalRecord> getViewOnlyMedicalRecords(Patient patient) {
        throw new UnsupportedOperationException("Yet to be implemented");
    }

    // used for read only view of prescriptions for display purposes
    public List<Prescription> getViewOnlyPrescriptions(Patient patient) {
        throw new UnsupportedOperationException("Yet to be implemented");
    }

    public Prescription getPrescription(Patient patient, String prescriptionCode) {
        checkPermission(prescriptionRepo.getPrescritionAsResource(prescriptionCode), BasicPermissions.READ);
        return prescriptionRepo.getPrescription(prescriptionCode);
    }

    public Prescription getLatestPrescription(Patient patient) {

        // can employ better ways to get Latest prescription

        List<Prescription> prescriptions = getPrescriptions(patient);
        Collections.sort(prescriptions, Collections.<Prescription>reverseOrder());
        Prescription result = prescriptions.get(0);
        checkPermission(result, BasicPermissions.READ);
        return result;
    }

    public MedicalRecord getLatestMedicalRecord(Patient patient) {

        // can employ better ways to get Latest medical record

        List<MedicalRecord> medRecs = getMedicalRecords(patient);
        Collections.sort(medRecs, Collections.<MedicalRecord>reverseOrder());
        MedicalRecord result = medRecs.get(0);
        checkPermission(result, BasicPermissions.READ);
        return result;
    }


    public List<Prescription> getPrescriptions(Patient patient) {
        if (!UserContext.getUser().getUsername().equals(patient.getUsername()))
            throw new AccessDeniedException("Access to all prescriptions is permitted only to woner");
            return prescriptionRepo.getPrescriptions(patient.getUsername());
    }

    public PatientRepo getPatientRepo() {
        return patientRepo;
    }

    public void setPatientRepo(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public MedicalRecordRepo getMedicalRecordRepo() {
        return medicalRecordRepo;
    }

    public void setMedicalRecordRepo(MedicalRecordRepo medicalRecordRepo) {
        this.medicalRecordRepo = medicalRecordRepo;
    }

    public PrescriptionRepo getPrescriptionRepo() {
        return prescriptionRepo;
    }

    public void setPrescriptionRepo(PrescriptionRepo prescriptionRepo) {
        this.prescriptionRepo = prescriptionRepo;
    }

    public PermissionGrantingManager getPermissionGrantingManager() {
        return permissionGrantingManager;
    }

    public void setPermissionGrantingManager(PermissionGrantingManager permissionGrantingManager) {

        this.permissionGrantingManager = permissionGrantingManager;
    }

    private void checkPermission(Resource resource, Permission permission) {
        if (permissionGrantingManager == null) return;
        if (!permissionGrantingManager.isGranted(UserContext.getUser(), resource, permission))
            throw new AccessDeniedException("Access denied to user " + UserContext.getUser().getType() + ":" + UserContext.getUser().getId() + "on resource " + resource.getType() + ":" + resource.getId());
    }
}
