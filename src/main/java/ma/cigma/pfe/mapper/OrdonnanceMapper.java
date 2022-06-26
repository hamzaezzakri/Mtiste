package ma.cigma.pfe.mapper;

import ma.cigma.pfe.dto.ConsultationDTO;
import ma.cigma.pfe.dto.OrdonnanceDTO;
import ma.cigma.pfe.dto.PatientDTO;
import ma.cigma.pfe.dto.RendezVousDTO;
import ma.cigma.pfe.model.Consultation;
import ma.cigma.pfe.model.Ordonnance;
import ma.cigma.pfe.model.Patient;
import ma.cigma.pfe.model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 8:01 PM
 */

@Mapper(componentModel = "spring")
public interface OrdonnanceMapper {

    OrdonnanceDTO toOrdonnanceDTO(Ordonnance Ordonnance);
    @Mapping(target = "consultation", ignore = true)
    Ordonnance toOrdonnance(OrdonnanceDTO OrdonnanceDTO);
    List<OrdonnanceDTO> toOrdonnanceDTO(List<Ordonnance> Ordonnances);
    List<Ordonnance> toOrdonnance(List<OrdonnanceDTO> OrdonnanceDTOS);
    @Mapping(target = "ordonnance", ignore = true)
    ConsultationDTO toConsultationDTO(Consultation consultation);
    @Mapping(target = "consultation", ignore = true)
    RendezVousDTO toRendezVousDTO(RendezVous rendezVous);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
}
