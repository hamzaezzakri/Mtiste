package ma.cigma.pfe.dao;

import ma.cigma.pfe.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:54 PM
 */

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
