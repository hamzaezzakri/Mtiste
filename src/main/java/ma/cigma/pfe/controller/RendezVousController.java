package ma.cigma.pfe.controller;

import ma.cigma.pfe.model.RendezVous;
import ma.cigma.pfe.service.IPatientService;
import ma.cigma.pfe.service.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/28/2022 8:59 PM
 */

@RestController
@RequestMapping("rendez-vous")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RendezVousController {

    @Autowired
    private IRendezVousService rendezVousService;
    @Autowired
    private IPatientService patientService;

    @GetMapping("/all-rendez-vous")
    public ResponseEntity<List<RendezVous>> getAll() {

        return ResponseEntity.ok(rendezVousService.getAll().stream()
                .filter(rdv -> rdv.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @PostMapping("/save-consultation")
    public ResponseEntity<?> saveConsultation(@RequestBody @Valid RendezVous rendezVous) {

        if (rendezVous.getConsultation() == null || rendezVous.getConsultation().getOrdonnance() != null) {

            return ResponseEntity.badRequest().body("merci d'associer une consultation au rendez-vous sans ordonnance");
        }

        RendezVous oldRendezVous = rendezVousService.getById(rendezVous.getId());
        if (oldRendezVous == null) {

            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");
        }

        if (oldRendezVous.getConsultation() != null) {

            return ResponseEntity.badRequest().body("rendez-vous ne peut avoir qu'une seule consultation");
        }

        oldRendezVous.setConsultation(rendezVous.getConsultation());
        rendezVousService.addConsultationToRendezVous(oldRendezVous);
        return ResponseEntity.ok().body("consultation enregistrée avec succès");
    }

    @PutMapping("/update-rendez-vous/{id}")
    public ResponseEntity<?> updateRendezVous(@RequestBody @Valid RendezVous rendezVous, @PathVariable("id") Long idRendezVous) {

        if (rendezVous.getPatient() != null || rendezVous.getConsultation() != null) {

            return ResponseEntity.badRequest().body("merci de ne pas associer un patient ou une consultation au rendez-vous");
        }

        if (!rendezVousService.existsById(idRendezVous))
            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");

        if (patientService.existsByDateVisiteAndHeureVisite(rendezVous.getDateVisite(), rendezVous.getHeureVisite()))
            return ResponseEntity.badRequest().body("rendez-vous existe déjà, veuillez choisir une autre date");

        rendezVousService.updateRendezVous(rendezVous, idRendezVous);
        return ResponseEntity.ok().body("rendez-vous enregistré avec succès");
    }

    @DeleteMapping("/delete-rendez-vous/{id}")
    public ResponseEntity<?> deleteRendezVous(@PathVariable("id") Long idRendezVous) {

        if (!rendezVousService.existsById(idRendezVous))
            return ResponseEntity.badRequest().body("rendez-vous n'existe pas");

        rendezVousService.deleteRendezVous(idRendezVous);
        return ResponseEntity.ok().body("rendez-vous supprimé avec succès");
    }
}
