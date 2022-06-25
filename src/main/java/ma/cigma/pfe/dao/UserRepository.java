package ma.cigma.pfe.dao;

import ma.cigma.pfe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:50 PM
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}
