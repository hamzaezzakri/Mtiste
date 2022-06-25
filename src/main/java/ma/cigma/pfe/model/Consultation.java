package ma.cigma.pfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:06 PM
 */

@Data
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("consultation")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rendezVous_id")
    private RendezVous rendezVous;
    @JsonIgnoreProperties("consultation")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "consultation")
    private Ordonnance ordonnance;
}
