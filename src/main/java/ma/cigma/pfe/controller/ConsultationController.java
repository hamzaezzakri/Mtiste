package ma.cigma.pfe.controller;

import ma.cigma.pfe.model.Consultation;
import ma.cigma.pfe.service.IConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 5:47 PM
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private IConsultationService consultationService;

    @GetMapping("/all-consultations")
    public ResponseEntity<List<Consultation>> getAll(){

        return ResponseEntity.ok(consultationService.getAll().stream()
                .filter(c -> c.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @PostMapping("/save-ordonnance")
    public ResponseEntity<?> saveOrdonnance(@RequestBody @Valid Consultation consultation){

        if(consultation.getOrdonnance() == null){

            return ResponseEntity.badRequest().body("merci d'associer une ordonnance à la consultation");
        }

        Consultation oldConsultation = consultationService.getById(consultation.getId());
        if(oldConsultation == null) {

            return ResponseEntity.badRequest().body("consultation n'existe pas");
        }

        if(oldConsultation.getOrdonnance() != null){

            return ResponseEntity.badRequest().body("consultation ne peut avoir qu'une seule ordonnance");
        }

        oldConsultation.setOrdonnance(consultation.getOrdonnance());
        consultationService.addOrdonnanceToConsultation(oldConsultation);
        return ResponseEntity.status(HttpStatus.CREATED).body("ordonnance enregistrée avec succès");
    }

    @PutMapping("/update-consultation/{id}")
    public ResponseEntity<?> updateConsultation(@RequestBody @Valid Consultation consultation, @PathVariable("id") Long idRConsultation){

        if(consultation.getRendezVous() != null || consultation.getOrdonnance() != null){

            return ResponseEntity.badRequest().body("merci de ne pas associer un rendez-vous ou une ordonnance au consultation");
        }

        if(!consultationService.existsById(idRConsultation))
            return ResponseEntity.badRequest().body("consultation n'existe pas");

        consultationService.updateConsultation(consultation, idRConsultation);
        return ResponseEntity.ok().body("consultation enregistrée avec succès");
    }

    @DeleteMapping("/delete-consultation/{id}")
    public ResponseEntity<?> deleteConsultation(@PathVariable("id") Long idConsultation){

        if(!consultationService.existsById(idConsultation))
            return ResponseEntity.badRequest().body("consultation n'existe pas");

        consultationService.deleteConsultation(idConsultation);
        return ResponseEntity.ok().body("consultation supprimée avec succès");
    }

}

