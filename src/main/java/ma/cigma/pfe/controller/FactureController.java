package ma.cigma.pfe.controller;

import ma.cigma.pfe.model.Facture;
import ma.cigma.pfe.service.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 10:31 PM
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/facture")
public class FactureController {

    @Autowired
    private IFactureService factureService;

    @GetMapping("/all-factures")
    public ResponseEntity<List<Facture>> getAll(){

        return ResponseEntity.ok(factureService.getAll().stream()
                .filter(f -> f.getIsEnabled())
                .collect(Collectors.toList()));
    }

    @PutMapping("/update-facture/{id}")
    public ResponseEntity<?> updateFacture(@RequestBody @Valid Facture facture, @PathVariable("id") Long idRFacture){

        if(facture.getPatient() != null){

            return ResponseEntity.badRequest().body("merci de ne pas associer un patient au facture");
        }

        if(!factureService.existsById(idRFacture))
            return ResponseEntity.badRequest().body("facture n'existe pas");

        factureService.updateFacture(facture, idRFacture);
        return ResponseEntity.ok().body("facture enregistrée avec succès");
    }

    @DeleteMapping("/delete-facture/{id}")
    public ResponseEntity<?> deleteFacture(@PathVariable("id") Long idFacture){

        if(!factureService.existsById(idFacture))
            return ResponseEntity.badRequest().body("facture n'existe pas");

        factureService.deleteFacture(idFacture);
        return ResponseEntity.ok().body("facture supprimée avec succès");
    }
}

