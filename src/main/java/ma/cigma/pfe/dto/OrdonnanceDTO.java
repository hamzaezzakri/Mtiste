package ma.cigma.pfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 7:51 PM
 */

@Data
public class OrdonnanceDTO {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties("ordonnance")
    private ConsultationDTO consultation;
}
