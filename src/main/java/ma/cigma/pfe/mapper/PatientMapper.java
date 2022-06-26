package ma.cigma.pfe.mapper;

import ma.cigma.pfe.dto.*;
import ma.cigma.pfe.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 8:01 PM
 */

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toPatientDTO(Patient patient);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    Patient toPatient(PatientDTO patientDTO);
    List<PatientDTO> toPatientDTO(List<Patient> patients);
    List<Patient> toPatient(List<PatientDTO> patientDTOS);
    @Mapping(target = "patient", ignore = true)
    RendezVousDTO toRendezVousDTO(RendezVous rendezVous);
    @Mapping(target = "patient", ignore = true)
    FactureDTO toFactureDTO(Facture facture);
    @Mapping(target = "rendezVous", ignore = true)
    ConsultationDTO toConsultationDTO(Consultation consultation);
    @Mapping(target = "consultation", ignore = true)
    OrdonnanceDTO toOrdonnanceDTO(Ordonnance ordonnance);
}
