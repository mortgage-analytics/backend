package Group28.Backend.controller;

import Group28.Backend.Payload.SigninRequest;
import Group28.Backend.Payload.SignupRequest;
import Group28.Backend.domain.User;
import Group28.Backend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class LoginController
{
  @Autowired
  UserService userService;

  // The ? means you can return anything inside the response object, any class
  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest)
  {
    // Using get methods to retrieve email and password
    String email = signinRequest.getEmail();
    String password = signinRequest.getPassword();
    boolean isAuthorized = userService.isAuthorized(email, password);   // authorize the email and password used in signinRequest

    if (isAuthorized)
    {
      Cookie cookie = new Cookie("user_info", email + ":" + "ROLE_USER");
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
      return new ResponseEntity<>("Cookie set successfully!", headers, HttpStatus.OK);
    } else
    {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest signupAttempt)
  {
    // Check if password meets requirements: 8 characters, number, capital, special character
    String password = signupAttempt.getPassword();
    String specialCharacters = "!@#$%^&*()-+=<>?";

    // Check if password is at least 8 characters long
    if (password.length() < 8)
    {
      return ResponseEntity.badRequest().body("Password must include 8 characters");
    }

    boolean containsSpecChar = false;
    boolean containsDigit = false;

    // Check password for at least one uppercase letter
    boolean containsUppercase = !password.equals(password.toLowerCase());

    // Check password for special characters
    for (char curr : password.toCharArray()) {

        if (specialCharacters.contains(String.valueOf(curr))) {
            containsSpecChar = true;
        }
        if(Character.isDigit(curr)){
            containsDigit = true;
        }
    }

    // Check conditions of all booleans to see if password is valid
    if (!containsUppercase || !containsDigit || !containsSpecChar) {
        return ResponseEntity.badRequest().body("Password must include a number, capital, and special character");

    }

    // Create user if email not in use already
    String email = signupAttempt.getEmail();
    password = signupAttempt.getPassword();
    User newUser = new User(email, password);

    // Add user to repository
    if(userService.addUser(newUser)){
        return ResponseEntity.ok().build();
    }

    return ResponseEntity.badRequest().build();
  }


  @PreAuthorize("isAuthenticated()")
  @PostMapping("/signout")
  public ResponseEntity<java.lang.Object> signout(HttpServletRequest request, HttpServletResponse response)
  {
    // Invalidate user's session
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

    // Clear the authentication cookie
    Cookie cookie = new Cookie("user_info", null);
    cookie.setMaxAge(0); // Set the cookie's max age to 0, effectively deleting it
    cookie.setPath("/"); // Set the cookie path to ensure it's cleared across the entire application
    response.addCookie(cookie);

    return ResponseEntity.ok().body("Signout successful");
  }
}
