package ma.cigma.pfe.service;

import ma.cigma.pfe.model.RendezVous;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/28/2022 8:57 PM
 */

public interface IRendezVousService {

    List<RendezVous> getAll();
    void addConsultationToRendezVous(RendezVous rendezVous);
    void updateRendezVous(RendezVous rendezVous, Long idRendezVous);
    RendezVous getById(Long idRendezVous);
    boolean existsById(Long idRendezVous);
    void deleteRendezVous(Long idRendezVous);
}
