package ma.cigma.pfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 7:47 PM
 */

@Data
public class ConsultationDTO {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("consultation")
    private RendezVousDTO rendezVous;
    @JsonIgnoreProperties("consultation")
    private OrdonnanceDTO ordonnance;
}
