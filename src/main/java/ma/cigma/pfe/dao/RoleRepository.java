package ma.cigma.pfe.dao;

import ma.cigma.pfe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:51 PM
 */

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(String role);
    boolean existsByRole(String role);
}
