package ma.cigma.pfe.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/26/2022 7:52 PM
 */

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private List<RoleDTO> roles = new ArrayList<>();
}
