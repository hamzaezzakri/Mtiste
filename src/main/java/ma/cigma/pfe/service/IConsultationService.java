package ma.cigma.pfe.service;

import ma.cigma.pfe.model.Consultation;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 5:45 PM
 */

public interface IConsultationService {

    List<Consultation> getAll();
    void addOrdonnanceToConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation, Long idConsultation);
    Consultation getById(Long idConsultation);
    boolean existsById(Long idConsultation);
    void deleteConsultation(Long idConsultation);
}
