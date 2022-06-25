package ma.cigma.pfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:04 PM
 */

@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue
    private Long id;
    private String cin;
    private String nomPrenom;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private LocalDate dateNaissance;
    private String email;
    private String adresse;
    private String telephone;
    private LocalDateTime createdAt;
    private Boolean isEnabled;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties("patient")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    List<RendezVous> rendezVous = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties("patient")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    List<Facture> factures = new ArrayList<>();

}
