package Group28.Backend.service;

import Group28.Backend.domain.User;
import Group28.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

   
 * @param email The email of the user to retrieve
 * @return The user object if found, or null if not found
 */
public User findUserByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
}


    /**
     *
   
 * @param user The user object to add to the repository
 * @return True if the user was added successfully (does not already exist), false otherwise
 */
public boolean addUserIfNotExists(User user) {
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

    if (existingUser.isPresent()) {
        // User with the given email already exists
        return false;
    } else {
        // User does not exist, add to the repository
        userRepository.save(user);
        return true;
    }
}


 * Checks if a user with the given email and password exists and is authorized.
 *
 * @param email    The email of the user
 * @param password The password to check
 * @return True if the user is authorized, false otherwise
 */
public boolean isUserAuthorized(String email, String password) {
    User user = userRepository.findByEmail(email).orElse(null);
    return user != null && BCrypt.checkpw(password, user.getPassword());
}

    public boolean emailInUse(String email)
    {
        return false;
    }
}
