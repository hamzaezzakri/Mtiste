package ma.cigma.pfe;

import ma.cigma.pfe.dao.UserRepository;
import ma.cigma.pfe.model.Role;
import ma.cigma.pfe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MtisteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MtisteApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Role role = new Role();
		role.setRole("ADMIN");
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.getRoles().add(role);
		userRepository.save(user);
	}
}
