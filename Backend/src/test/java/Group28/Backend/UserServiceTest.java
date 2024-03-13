package Group28.Backend;
import Group28.Backend.repository.*;
import Group28.Backend.domain.*;
import Group28.Backend.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User testUser;

    @Before
    public void setup(){
        testUser = new User("test@something.com", "password");
    }

    @Test
    public void addUserTest(){
        assertEquals(testUser, userService.getUser("test@something.com"));
    }

    @Test
    public void addUserTest(){
        assertTrue(userService.addUser(testUser));
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
