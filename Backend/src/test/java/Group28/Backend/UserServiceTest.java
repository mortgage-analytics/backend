package Group28.Backend;
import Group28.Backend.repository.*;
import Group28.Backend.domain.*;
import Group28.Backend.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    public UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User testUser;

    // Default constructor
    public UserServiceTest() {
    }


    @Before
    public void setup(){
        testUser = new User("test@something.com", "password");
        when(userRepository.findByEmail("test@something.com")).thenReturn(testUser);

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
