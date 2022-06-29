package ma.cigma.pfe.service;

import ma.cigma.pfe.dao.OrdonnanceRepository;
import ma.cigma.pfe.model.Ordonnance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/29/2022 10:25 PM
 */

@Service
public class OrdonnanceServiceImpl implements IOrdonnanceService{

    @Autowired
    private OrdonnanceRepository ordonnanceRepository;
    @Override
    public List<Ordonnance> getAll() {

        return ordonnanceRepository.findAll();
    }

    @Override
    public void updateOrdonnance(Ordonnance ordonnance, Long idOrdonnance) {

        Ordonnance oldOrdonnance = ordonnanceRepository.findById(idOrdonnance).get();
        ordonnance.setId(oldOrdonnance.getId());
        ordonnance.setCreatedAt(oldOrdonnance.getCreatedAt());
        ordonnance.setIsEnabled(oldOrdonnance.getIsEnabled());
        ordonnance.setConsultation(oldOrdonnance.getConsultation());
        ordonnanceRepository.save(ordonnance);
    }

    @Override
    public void deleteOrdonnance(Long idOrdonnance) {

        Ordonnance ordonnance = ordonnanceRepository.findById(idOrdonnance).get();
        ordonnance.setIsEnabled(false);
        ordonnanceRepository.save(ordonnance);
    }

    @Override
    public boolean existsById(Long idOrdonnance) {

        return ordonnanceRepository.existsById(idOrdonnance);
    }
}

