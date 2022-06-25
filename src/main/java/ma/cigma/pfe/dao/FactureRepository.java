package ma.cigma.pfe.dao;

import ma.cigma.pfe.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:53 PM
 */

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
