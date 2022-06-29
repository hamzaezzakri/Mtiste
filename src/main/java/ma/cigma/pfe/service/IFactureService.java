package ma.cigma.pfe.service;

import ma.cigma.pfe.model.Facture;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 10:30 PM
 */

public interface IFactureService {

    List<Facture> getAll();
    void updateFacture(Facture facture, Long idFacture);
    boolean existsById(Long idFacture);
    void deleteFacture(Long idFacture);
}
