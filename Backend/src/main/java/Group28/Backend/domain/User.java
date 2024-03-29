package Group28.Backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Document
public class User
{
    @Id
    private String email;
    private String password; // Password is hashed using BCrypt to securely store it. You can use BCrypts methods to check

    // IMPORTANT If this is not here find() for the userRepo seems to call the other constructor, that cannot be
    // allowed to happen as it will hash an already hashed password
    public User()
    {

    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
