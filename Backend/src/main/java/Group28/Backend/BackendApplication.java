package Group28.Backend;

import Group28.Backend.Security.AuthEntryPointJwt;
import Group28.Backend.Security.JwtAuthFilter;
import Group28.Backend.controller.DataController;
import Group28.Backend.controller.LoginController;
import Group28.Backend.domain.User;
import Group28.Backend.repository.DataRepository;
import Group28.Backend.repository.UserRepository;
import Group28.Backend.service.DataService;
import Group28.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements ApplicationRunner
{
	@Autowired
	LoginController loginController;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;


	@Autowired
	DataController dataController;

	@Autowired
	DataService dataService;

	@Autowired
	DataRepository dataRepository;


	@Autowired
	JwtAuthFilter jwtAuthFilter;

	@Autowired
	AuthEntryPointJwt authEntryPointJwt;

	public static void main(String[] args)
	{
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		// Add users
		userRepository.save(new User("byrnel58@tcd.ie", "Test12345_"));
	}
}
