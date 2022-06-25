package ma.cigma.pfe.dao;

import ma.cigma.pfe.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:52 PM
 */

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
