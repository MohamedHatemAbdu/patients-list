package com.me.data.patient.model.mapper;

import com.me.data.patient.model.PatientData;
import com.me.domain.patient.entity.PatientEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PatientDataEntityMapper {

    public PatientEntity transform(PatientData patientData) {
        PatientEntity patient = null;
        if (patientData != null) {
            patient = new PatientEntity(patientData.getPatientId());
            patient.setFullName(patientData.getFullName());
            patient.setEmail(patientData.getEmail());
            patient.setAge(patientData.getAge());
            patient.setGender(patientData.getGender());
        }
        return patient;
    }

    public List<PatientEntity> transform(Collection<PatientData> patientDataCollection) {
        final List<PatientEntity> patientList = new ArrayList<>(20);
        for (PatientData patientData : patientDataCollection) {
            final PatientEntity patient = transform(patientData);
            if (patient != null) {
                patientList.add(patient);
            }
        }
        return patientList;
    }

}
