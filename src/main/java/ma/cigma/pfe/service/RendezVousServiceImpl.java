package ma.cigma.pfe.service;

import ma.cigma.pfe.dao.RendezVousRepository;
import ma.cigma.pfe.model.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/28/2022 8:57 PM
 */

@Service
public class RendezVousServiceImpl implements IRendezVousService{


    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public List<RendezVous> getAll() {

        return rendezVousRepository.findAll();
    }

    @Override
    public void addConsultationToRendezVous(RendezVous rendezVous) {

        rendezVous.getConsultation().setRendezVous(rendezVous);
        rendezVous.getConsultation().setCreatedAt(LocalDateTime.now());
        rendezVous.getConsultation().setIsEnabled(true);
        rendezVousRepository.save(rendezVous);
    }

    @Override
    public void updateRendezVous(RendezVous rendezVous, Long idRendezVous) {

        RendezVous oldRendezVous = rendezVousRepository.findById(idRendezVous).get();
        rendezVous.setId(oldRendezVous.getId());
        rendezVous.setCreatedAt(oldRendezVous.getCreatedAt());
        rendezVous.setIsEnabled(oldRendezVous.getIsEnabled());
        rendezVous.setPatient(oldRendezVous.getPatient());
        rendezVousRepository.save(rendezVous);
    }

    @Override
    public void deleteRendezVous(Long idRendezVous) {

        RendezVous rendezVous = rendezVousRepository.findById(idRendezVous).get();
        rendezVous.setIsEnabled(false);
        rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous getById(Long idRendezVous) {

        return rendezVousRepository.findById(idRendezVous).orElse(null);
    }

    @Override
    public boolean existsById(Long idRendezVous) {

        return rendezVousRepository.existsById(idRendezVous);
    }
}

