package Group28.Backend.service;

import Group28.Backend.domain.User;
import Group28.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService
{
  @Autowired
  UserRepository userRepository;

  /**
   * @param email The email of the user to retrieve
   * @return The user object if found, or null if not found
   */
  public User getUser(String email)
  {
    return userRepository.findById(email).orElse(null);
  }

  /**
   * @param user The user object to add to the repository
   * @return True if the user was added successfully (does not already exist), false otherwise
   */
  public boolean addUser(User user)
  {
    Optional<User> existingUser = userRepository.findById(user.getEmail());

    if (existingUser.isPresent())
    {
      // User with the given email already exists
      return false;
    } else
    {
      // User does not exist, add to the repository
      userRepository.save(user);
      return true;
    }
  }

  /**
   * Checks if a user with the given email and password exists and is authorized.
   *
   * @param email    The email of the user
   * @param password The password to check
   * @return True if the user is authorized, false otherwise
   */
  public boolean isAuthorized(String email, String password)
  {
    User user = userRepository.findById(email).orElse(null);
    return user != null && BCrypt.checkpw(password, user.getPassword());
  }

  public boolean emailInUse(String email)
  {
    return false;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    User user = userRepository.findById(username).orElse(null);

    if (user == null)
    {
      return null; // TODO maybe return something else or throw exception?
    }

    return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles("USER")
            .build();
  }
}
