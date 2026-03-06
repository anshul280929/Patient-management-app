package com.pm.patient_service.service;


import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    //Dependency injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //Get Patient
    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();

    }
    //Create a Patient entity
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Patient with this email already exists"+patientRequestDTO.getEmail());
        }

        Patient newPatient=patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );

        return PatientMapper.toDTO(newPatient);
    }
    //Update a Patient entity
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        //get the patient by id
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new PatientNotFoundException("Patient with id "+id+" not found")
        );
        //check if the email is already used by another patient
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Patient with this email already exists"+patientRequestDTO.getEmail());
        }
        //update the patient entity
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setBirthDate(patient.getBirthDate());
        //Registered date should not be updated once after registered

        Patient updatedPatient=patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
     //Delete a Patient entity


}
