package Group28.Backend;
import Group28.Backend.repository.*;
import Group28.Backend.domain.*;
import Group28.Backend.service.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertFalse;

@SpringBootTest
public class ApplicationServiceTest {

    @Mock
    public UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User testUser;

    public ApplicationServiceTest(){

    }

    @Test
    public void Test(){
        assertFalse(false);
    }


}
