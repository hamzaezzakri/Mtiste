package ma.cigma.pfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:05 PM
 */

@Data
@Entity
@Table(name = "facture")
public class Facture {

    @Id
    @GeneratedValue
    private Long id;
    private Double montant;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"factures","rendezVous"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
