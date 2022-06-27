package ma.cigma.pfe.dao;

import ma.cigma.pfe.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:53 PM
 */

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    boolean existsByDateVisiteAndHeureVisite(LocalDate dateVisite, LocalTime heureVisite);
}
