package demo;

import demo.models.postgres.Role;
import demo.models.postgres.User;
import demo.repository.postges.RoleRepository;
import demo.repository.postges.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Rozpoczęto działanie aplikacji.");

        // Dodanie ról
        addRoleIfNotExists("ADMIN");
        addRoleIfNotExists("USER");

        // Dodanie użytkownika admina
        addAdminUserIfNotExists("admin", "admin123", "ADMIN");

        logger.info("Aplikacja zainicjalizowana pomyślnie.");
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        logger.info("Zakończono działanie aplikacji.");
        System.out.println("Zakończono działanie.");
    }

    private Role addRoleIfNotExists(String roleName) {
        return roleRepository.findByName(roleName).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName(roleName);
            return roleRepository.save(newRole); // Zapisanie nowej roli w bazie danych
        });
    }


    private void addAdminUserIfNotExists(String username, String password, String roleName) {
        if (!userRepository.existsByUsername(username)) {
            // Pobranie roli z bazy danych (zarządzany przez Hibernate)
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

            // Odświeżenie encji roli (opcjonalne, dla pewności)
            role = roleRepository.saveAndFlush(role);

            // Utworzenie użytkownika i przypisanie zarządzanej roli
            User adminUser = new User();
            adminUser.setUsername(username);
            adminUser.setPassword(passwordEncoder.encode(password));
            adminUser.setRoles(Set.of(role));
            userRepository.save(adminUser);

            logger.info("Dodano użytkownika: " + username);
        } else {
            logger.info("Użytkownik " + username + " już istnieje.");
        }
    }





}
