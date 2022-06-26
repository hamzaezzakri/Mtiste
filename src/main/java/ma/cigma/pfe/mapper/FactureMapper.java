package ma.cigma.pfe.mapper;

import ma.cigma.pfe.dto.FactureDTO;
import ma.cigma.pfe.dto.PatientDTO;
import ma.cigma.pfe.model.Facture;
import ma.cigma.pfe.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 8:01 PM
 */

@Mapper(componentModel = "spring")
public interface FactureMapper {

    FactureDTO toFactureDTO(Facture facture);
    @Mapping(target = "patient", ignore = true)
    Facture toFacture(FactureDTO factureDTO);
    List<FactureDTO> toFactureDTO(List<Facture> factures);
    List<Facture> toFacture(List<FactureDTO> factureDTOS);
    @Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "factures", ignore = true)
    PatientDTO toPatientDTO(Patient patient);
}
