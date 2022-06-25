package ma.cigma.pfe.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:05 PM
 */

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;
}
