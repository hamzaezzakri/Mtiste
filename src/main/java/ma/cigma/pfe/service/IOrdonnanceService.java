package ma.cigma.pfe.service;

import ma.cigma.pfe.model.Ordonnance;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 10:24 PM
 */

public interface IOrdonnanceService {

    List<Ordonnance> getAll();
    void updateOrdonnance(Ordonnance ordonnance, Long idOrdonnance);
    boolean existsById(Long idOrdonnance);
    void deleteOrdonnance(Long idOrdonnance);
}
