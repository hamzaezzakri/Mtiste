package ma.cigma.pfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 7:48 PM
 */

@Data
public class FactureDTO {

    private Long id;
    private Double montant;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"factures","rendezVous"})
    private PatientDTO patient;
}
