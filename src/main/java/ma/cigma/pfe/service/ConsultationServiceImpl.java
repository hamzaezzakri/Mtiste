package ma.cigma.pfe.service;

import ma.cigma.pfe.dao.ConsultationRepository;
import ma.cigma.pfe.model.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 5:46 PM
 */

@Service
public class ConsultationServiceImpl implements IConsultationService{

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public List<Consultation> getAll() {

        return consultationRepository.findAll();
    }

    @Override
    public void addOrdonnanceToConsultation(Consultation consultation) {

        consultation.getOrdonnance().setConsultation(consultation);
        consultation.getOrdonnance().setCreatedAt(LocalDateTime.now());
        consultation.getOrdonnance().setIsEnabled(true);
        consultationRepository.save(consultation);
    }

    @Override
    public void updateConsultation(Consultation consultation, Long idConsultation) {

        Consultation oldConsultation = consultationRepository.findById(idConsultation).get();
        consultation.setId(oldConsultation.getId());
        consultation.setCreatedAt(oldConsultation.getCreatedAt());
        consultation.setIsEnabled(oldConsultation.getIsEnabled());
        consultation.setRendezVous(oldConsultation.getRendezVous());
        consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long idConsultation) {

        Consultation consultation = consultationRepository.findById(idConsultation).get();
        consultation.setIsEnabled(false);
        consultationRepository.save(consultation);
    }

    @Override
    public Consultation getById(Long idConsultation) {

        return consultationRepository.findById(idConsultation).orElse(null);
    }

    @Override
    public boolean existsById(Long idConsultation) {

        return consultationRepository.existsById(idConsultation);
    }
}
