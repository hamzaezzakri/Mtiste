package ma.cigma.pfe.controller;

import ma.cigma.pfe.model.Patient;
import ma.cigma.pfe.model.RendezVous;
import ma.cigma.pfe.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/27/2022 9:01 PM
 */

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping("/all-patients")
    public ResponseEntity<List<Patient>>getAll(){

        return ResponseEntity.ok(patientService.getAll().stream()
                .filter(p -> p.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @PostMapping("/save-patient")
    public ResponseEntity<?>savePatient(@RequestBody @Valid Patient patient){

        if(patient.getRendezVous().size() != 1 || patient.getFactures().size() != 0 || patient.getRendezVous().get(0).getConsultation() != null){

            return ResponseEntity.badRequest().body("veuillez associer un rendez-vous au patient sans consultation");
        }

        if(patientService.existsByDateVisiteAndHeureVisite(patient.getRendezVous().get(0).getDateVisite(),
                patient.getRendezVous().get(0).getHeureVisite()))
            return ResponseEntity.badRequest().body("rendez vous existe déjà");

        Patient oldPatient = patientService.findByCin(patient.getCin());
        if(oldPatient == null) {

            patientService.addPatient(patient);
            return ResponseEntity.ok("rendez-vous enregistré avec succès");
        }

        if(oldPatient.getRendezVous().get(oldPatient.getRendezVous().size()-1).getDateVisite().compareTo(patient.getRendezVous().get(0).getDateVisite()) == 0){

            return ResponseEntity.badRequest().body("vous ne pouvez pas prendre plusieurs rendez-vous dans la même journée");
        }

        List<RendezVous> rendezVous = oldPatient.getRendezVous().stream()
                .filter(rdv -> rdv.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                .collect(Collectors.toList());

        if(rendezVous.size() > 1){

            return ResponseEntity.badRequest().body("vous ne pouvez pas réserver plusieurs rendez-vous le même jour");
        }

        patient.setId(oldPatient.getId());
        patient.setCreatedAt(oldPatient.getCreatedAt());
        patient.setIsEnabled(oldPatient.getIsEnabled());
        patientService.addRendezVousToPatient(patient);
        return ResponseEntity.ok().body("rendez-vous enregistré avec succès");
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<?>updatePatient(@RequestBody @Valid Patient patient, @PathVariable("id") Long idPatient){

        if(patient.getRendezVous().size() != 0 || patient.getFactures().size() != 0){

            return ResponseEntity.badRequest().body("merci de ne pas associer de rendez-vous ou de factures au patient");
        }

        if(!patientService.existsById(idPatient))
            return ResponseEntity.badRequest().body("patient n'existe pas");

        patientService.updatePatient(patient, idPatient);
        return ResponseEntity.ok().body("patient enregistré avec succès");
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<?>deletePatient(@PathVariable("id") Long idPatient){

        if(!patientService.existsById(idPatient))
            return ResponseEntity.badRequest().body("patient n'existe pas");

        patientService.deletePatient(idPatient);
        return ResponseEntity.ok().body("patient supprimé avec succès");
    }

    @PostMapping("/save-facture")
    public ResponseEntity<?>saveFacture(@RequestBody @Valid Patient patient){

        if(patient.getFactures().size() != 1 || patient.getRendezVous().size() != 0){

            return ResponseEntity.badRequest().body("veuillez associer une facture au patient");
        }

        Patient oldPatient = patientService.findByNomPrenom(patient.getNomPrenom());
        if(oldPatient == null) {

            return ResponseEntity.badRequest().body("patient n'existe pas");
        }

        oldPatient.setFactures(patient.getFactures());
        patientService.addFactureToPatient(oldPatient);
        return ResponseEntity.ok().body("facture enregistré avec succès");
    }

}

