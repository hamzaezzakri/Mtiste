package ma.cigma.pfe.controller;

import ma.cigma.pfe.model.Ordonnance;
import ma.cigma.pfe.service.IOrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 10:26 PM
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/ordonnance")
public class OrdonnanceController {

    @Autowired
    private IOrdonnanceService ordonnanceService;

    @GetMapping("/all-ordonnances")
    public ResponseEntity<List<Ordonnance>> getAll(){

        return ResponseEntity.ok(ordonnanceService.getAll().stream()
                .filter(ord -> ord.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @PutMapping("/update-ordonnance/{id}")
    public ResponseEntity<?> updateOrdonnance(@RequestBody @Valid Ordonnance ordonnance, @PathVariable("id") Long idROrdonnance){

        if(ordonnance.getConsultation() != null){

            return ResponseEntity.badRequest().body("merci de ne pas associer une consultation à l'ordonnance");
        }

        if(!ordonnanceService.existsById(idROrdonnance))
            return ResponseEntity.badRequest().body("ordonnance n'existe pas");

        ordonnanceService.updateOrdonnance(ordonnance, idROrdonnance);
        return ResponseEntity.ok().body("ordonnance enregistrée avec succès");
    }

    @DeleteMapping("/delete-ordonnance/{id}")
    public ResponseEntity<?> deleteOrdonnance(@PathVariable("id") Long idOrdonnance){

        if(!ordonnanceService.existsById(idOrdonnance))
            return ResponseEntity.badRequest().body("ordonnance n'existe pas");

        ordonnanceService.deleteOrdonnance(idOrdonnance);
        return ResponseEntity.ok().body("ordonnance supprimée avec succès");
    }
}

