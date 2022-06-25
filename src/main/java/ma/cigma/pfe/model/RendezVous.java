package ma.cigma.pfe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:05 PM
 */

@Data
@Entity
@Table(name = "rendezVous")
public class RendezVous {

    @Id
    @GeneratedValue
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisite;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime heureVisite;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @JsonIgnoreProperties({"rendezVous","factures"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @JsonIgnoreProperties("rendezVous")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rendezVous")
    private Consultation consultation;
}
