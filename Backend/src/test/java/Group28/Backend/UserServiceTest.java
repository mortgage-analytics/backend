package Group28.Backend;
import Group28.Backend.domain.*;
import Group28.Backend.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.repository.CrudRepository;
import Group28.Backend.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        userService = new UserService();
        testUser = new User("test@something.com", "password");
        userService.addUser(testUser);
        when(userRepository.findById("test@something.com")).thenReturn(Optional.ofNullable(testUser)); // Mocking findById method
    }

    @Test

    public void addUserTest(){
        assertEquals(testUser, userService.getUser("test@something.com"));
    }

    @Test
    public void addExistingUserTest(){
        assertTrue(userService.isAuthorized("test@something.com","password"));
    }

    @Test
    public void emailInUseTest(){
        assertFalse(userService.emailInUse("test@something.com"));
    }


}
