package ma.cigma.pfe.mapper;

import ma.cigma.pfe.dto.UserDTO;
import ma.cigma.pfe.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 8:02 PM
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTO(List<User> users);
    List<User> toUser(List<UserDTO> userDTOS);
}
