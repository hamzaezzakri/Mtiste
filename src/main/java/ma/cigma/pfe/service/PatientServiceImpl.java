package ma.cigma.pfe.service;

import ma.cigma.pfe.dao.PatientRepository;
import ma.cigma.pfe.dao.RendezVousRepository;
import ma.cigma.pfe.model.Etat;
import ma.cigma.pfe.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/27/2022 8:56 PM
 */

@Service
public class PatientServiceImpl implements IPatientService{

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public List<Patient> getAll() {

        return patientRepository.findAll();
    }

    @Override
    public void addPatient(Patient patient) {

        patient.setCreatedAt(LocalDateTime.now());
        patient.setIsEnabled(true);
        patient.getRendezVous().get(0).setPatient(patient);
        patient.getRendezVous().get(0).setEtat(Etat.EN_ATTENTE);
        patient.getRendezVous().get(0).setCreatedAt(LocalDateTime.now());
        patient.getRendezVous().get(0).setIsEnabled(true);
        patientRepository.save(patient);
    }

    @Override
    public void addRendezVousToPatient(Patient patient) {

        patient.getRendezVous().get(0).setPatient(patient);
        patient.getRendezVous().get(0).setEtat(Etat.EN_ATTENTE);
        patient.getRendezVous().get(0).setCreatedAt(LocalDateTime.now());
        patient.getRendezVous().get(0).setIsEnabled(true);
        patientRepository.save(patient);
    }

    @Override
    public void addFactureToPatient(Patient patient) {

        patient.getFactures().get(patient.getFactures().size()-1).setPatient(patient);
        patient.getFactures().get(patient.getFactures().size()-1).setCreatedAt(LocalDateTime.now());
        patient.getFactures().get(patient.getFactures().size()-1).setIsEnabled(true);
        patientRepository.save(patient);
    }

    @Override
    public Patient findByNomPrenom(String nomPrenom) {

        return patientRepository.findByNomPrenom(nomPrenom);
    }

    @Override
    public void updatePatient(Patient patient, Long idPatient) {

        Patient oldPatient = patientRepository.findById(idPatient).get();

        patient.setId(oldPatient.getId());
        patient.setCreatedAt(oldPatient.getCreatedAt());
        patient.setIsEnabled(oldPatient.getIsEnabled());
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long idPatient) {

        Patient patient = patientRepository.findById(idPatient).get();
        patient.setIsEnabled(false);
        patientRepository.save(patient);
    }

    @Override
    public boolean existsById(Long idPatient) {

        return patientRepository.existsById(idPatient);
    }

    @Override
    public boolean existsByDateVisiteAndHeureVisite(LocalDate dateVisite, LocalTime heureVisite) {

        return rendezVousRepository.existsByDateVisiteAndHeureVisite(dateVisite, heureVisite);
    }

    @Override
    public Patient findByCin(String cin) {

        return patientRepository.findByCin(cin);
    }
}
