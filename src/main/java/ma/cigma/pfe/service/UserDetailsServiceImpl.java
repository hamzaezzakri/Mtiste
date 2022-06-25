package ma.cigma.pfe.service;

import ma.cigma.pfe.dao.UserRepository;
import ma.cigma.pfe.model.Role;
import ma.cigma.pfe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 3:03 PM
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("Username Not Found Exception");

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),enabled,
                accountNonExpired,credentialsNonExpired,accountNonLocked,getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
