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
