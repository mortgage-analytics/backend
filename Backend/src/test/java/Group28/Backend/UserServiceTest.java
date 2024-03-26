package Group28.Backend;
import Group28.Backend.domain.*;
import Group28.Backend.service.*;
import Group28.Backend.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    private User testUser;

    // Default constructor
    public UserServiceTest() {
    }


    @Before
    public void setup(){
        testUser = new User("test@something.com", "password");
        userService.addUser(testUser);
    }

    @Test
    public void addUserTest(){
        when(userRepository.findById("test@something.com")).thenReturn(Optional.ofNullable(testUser)); // Mocking findById method
        assertEquals(testUser, userService.getUser("test@something.com"));
    }

    @Test
    public void addExistingUserTest(){
        when(userRepository.findById("test@something.com")).thenReturn(Optional.ofNullable(testUser)); // Mocking findById method
        assertTrue(userService.isAuthorized("test@something.com","password"));
    }

    @Test
    public void emailInUseTest(){
        when(userRepository.findById("test@something.com")).thenReturn(Optional.ofNullable(testUser)); // Mocking findById method
        assertFalse(userService.emailInUse("test@something.com"));
    }

}
