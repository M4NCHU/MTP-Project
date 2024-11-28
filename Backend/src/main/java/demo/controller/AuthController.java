package demo.controller;

import demo.models.postgres.Role;
import demo.models.postgres.User;
import demo.dto.RegisterRequest;
import demo.repository.postges.RoleRepository;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User newUser = new User(registerRequest.getUsername(), encodedPassword);

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        newUser.getRoles().add(userRole);

        userService.registerUser(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

}
