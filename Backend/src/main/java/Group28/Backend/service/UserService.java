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

    /**
     *
     * @param email The email of the requested user
     * @return      The user with email or null if non-existent
     */
   public User getUser(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     *
     * @param user The user object to add to the repository
     * @return     True if successful, false if not (if already exists fail)
     */
    public boolean addUser(User user)
    {
        for (User user : UserRepository) {
            if (user.getEmail().equals(email)) {
                return false;
            }
            else{
                userRepository.add(user);
                return true;
            }
        return false;
    }

    /**
     *
     * @param email    The email of the user
     * @param password The password entered
     * @return         Whether the credentials entered were correct or not
     */
     public boolean isAuthorized(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null && BCrypt.checkpw(password, user.getPassword());
    }

    public boolean emailInUse(String email)
    {
        return false;
    }
}
