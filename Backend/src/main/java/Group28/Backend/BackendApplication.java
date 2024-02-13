package Group28.Backend;

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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
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

	public static void main(String[] args)
	{
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		// Set admin user
		userService.addUser(new User("byrnel58@tcd.ie", "Test1234_"));

		if(userRepository.existsById("byrnel58@tcd.ie"))
		{
			System.out.println("Added");
		}
	}
}
