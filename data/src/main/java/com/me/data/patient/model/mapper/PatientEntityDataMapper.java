package com.me.data.patient.model.mapper;

import com.me.data.patient.model.PatientData;
import com.me.domain.patient.entity.PatientEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PatientEntityDataMapper {



    public PatientData transform(PatientEntity patientEntity) {
        PatientData patient = null;
        if (patientEntity != null) {
            patient = new PatientData(patientEntity.getPatientId());
            patient.setFullName(patientEntity.getFullName());
            patient.setEmail(patientEntity.getEmail());
            patient.setAge(patientEntity.getAge());
            patient.setGender(patientEntity.getGender());
        }
        return patient;
    }

    public List<PatientData> transform(Collection<PatientEntity> patientDataCollection) {
        final List<PatientData> patientList = new ArrayList<>(20);
        for (PatientEntity patientData : patientDataCollection) {
            final PatientData patient = transform(patientData);
            if (patient != null) {
                patientList.add(patient);
            }
        }
        return patientList;
    }
}
