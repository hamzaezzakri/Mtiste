package ma.cigma.pfe.mapper;

import ma.cigma.pfe.dto.RoleDTO;
import ma.cigma.pfe.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 8:02 PM
 */

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);
    Role toRole(RoleDTO roleDTO);
    List<RoleDTO> toRoleDTO(List<Role> roles);
    List<Role> toRole(List<RoleDTO> roleDTOS);
}
