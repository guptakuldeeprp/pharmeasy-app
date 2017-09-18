package com.pharmeasy.app.demo;

import com.pharmeasy.app.entity.Doctor;
import com.pharmeasy.app.entity.MedicalRecord;
import com.pharmeasy.app.entity.Patient;
import com.pharmeasy.app.entity.Prescription;
import com.pharmeasy.app.repo.MedicalRecordRepo;
import com.pharmeasy.app.repo.PatientRepo;
import com.pharmeasy.app.repo.PrescriptionRepo;
import com.pharmeasy.app.repo.impl.InMemoryMedicalRecordRepo;
import com.pharmeasy.app.repo.impl.InMemoryPatientRepo;
import com.pharmeasy.app.repo.impl.InMemoryPrescriptionRepo;
import com.pharmeasy.app.service.PatientService;
import com.pharmeasy.auth.AclService;
import com.pharmeasy.auth.DefaultPermissionGrantingManager;
import com.pharmeasy.auth.PermissionGrantingManager;
import com.pharmeasy.auth.UserContext;
import com.pharmeasy.auth.core.AclEntry;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.impl.InMemoryAclService;
import com.pharmeasy.auth.util.BasicPermissions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoClient {

    public static void main(String[] args) {

        ///////////////////// initialize reuqester/doctor/pharmacist ///////////////////
        Doctor doctor = new Doctor(1L, "sa", "Sandesh Agrawal");
        UserContext.setUser(doctor);


        //////////////////// Create dummy patients //////////////////////////

        Patient patient = new Patient(1L, "p1", "Kuldeep Gupta");
        Patient patient1 = new Patient(2L, "p2", "Sanjay Mishra");


        /////////////////////// Initialize medical record repo //////////////////
        Map<String, List<MedicalRecord>> sampleRecs = new HashMap<>();
        sampleRecs.put("p1", Arrays.asList(new MedicalRecord[]{
                new MedicalRecord(1L, patient, "m1p1", "dummy"),
                new MedicalRecord(2L, patient, "m2p1", "dummy"),
        }));
        sampleRecs.put("p2", Arrays.asList(new MedicalRecord[]{
                new MedicalRecord(3L, patient1, "m1p2", "dummy"),
                new MedicalRecord(4L, patient1, "m2p3", "dummy"),
        }));
        MedicalRecordRepo medicalRecordRepo = new InMemoryMedicalRecordRepo(sampleRecs);


        /////////////////////// Initialize prescription repo /////////////////////
        Map<String, List<Prescription>> samplePrescs = new HashMap<>();
        samplePrescs.put("p1", Arrays.asList(new Prescription[]{
                new Prescription(1L, patient, "pr1p1", "dummy"),
                new Prescription(2L, patient, "pr2p1", "dummy"),
        }));
        samplePrescs.put("p2", Arrays.asList(new Prescription[]{
                new Prescription(3L, patient1, "pr1p2", "dummy"),
                new Prescription(4L, patient1, "pr2p3", "dummy"),
        }));
        PrescriptionRepo prescriptionRepo = new InMemoryPrescriptionRepo(samplePrescs);


        /////////////////// Initialize patient repo ////////////////////
        PatientRepo patientRepo = new InMemoryPatientRepo();
        patientRepo.savePatient(patient);
        patientRepo.savePatient(patient1);


        ///////////////////////// Initialize PermissionGrantingManager /////////////////////////
        Map<Resource, List<AclEntry>> sampleAcls = new HashMap<>();
        Resource resource = medicalRecordRepo.getMedicalRecord("m1p1");
        sampleAcls.put(resource, Arrays.asList(new AclEntry[]{new AclEntry(1L, doctor, resource, BasicPermissions.READ, false)}));
        AclService aclService = new InMemoryAclService(sampleAcls);

        PermissionGrantingManager permissionGrantingManager = new DefaultPermissionGrantingManager(aclService);


        ////////////////////////// Run patient service /////////////////////////////
        PatientService patientService = new PatientService(patientRepo, medicalRecordRepo, prescriptionRepo, permissionGrantingManager);
        System.out.println(patientService.getPrescription(patient, "pr1p1"));

    }


}
