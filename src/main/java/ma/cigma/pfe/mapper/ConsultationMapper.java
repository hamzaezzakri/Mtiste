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
 * @CreatedAt 6/26/2022 8:00 PM
 */

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    ConsultationDTO toConsultationDTO(Consultation consultation);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "ordonnance", ignore = true)
    Consultation toConsultation(ConsultationDTO consultationDTO);
    List<ConsultationDTO> toConsultationDTO(List<Consultation> consultations);
    List<Consultation> toConsultation(List<ConsultationDTO> consultationDTOS);
    @Mapping(target = "consultation", ignore = true)
    RendezVousDTO toRendezVousDTO(RendezVous rendezVous);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
    @Mapping(target = "consultation", ignore = true)
    OrdonnanceDTO toOrdonnanceDTO(Ordonnance ordonnance);
}
