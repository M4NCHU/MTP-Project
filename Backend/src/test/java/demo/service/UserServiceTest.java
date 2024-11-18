package demo.service;

import demo.models.User;
import demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();

        userService.registerUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testExistsByUsername() {
        when(userRepository.existsByUsername("admin")).thenReturn(true);

        boolean exists = userService.existsByUsername("admin");

        assertTrue(exists);
        verify(userRepository, times(1)).existsByUsername("admin");
    }
}
