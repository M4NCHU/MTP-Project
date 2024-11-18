package demo.service;

import demo.models.User;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user registration and authentication.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user.
     *
     * @param user the user entity to register.
     */
    public void registerUser(User user) {
        userRepository.save(user);
    }

    /**
     * Checks if a user exists by username.
     *
     * @param username the username to check.
     * @return {@code true} if the user exists, otherwise {@code false}.
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
