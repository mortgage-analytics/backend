package Group28.Backend.controller;

import Group28.Backend.Payload.SigninRequest;
import Group28.Backend.Payload.SignupRequest;
import Group28.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/auth")
public class LoginController
{
    @Autowired
    UserService userService;

    // The ? means you can return anything inside the response object, any class

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest)
    {

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupAttempt)
    {
        // Check if password meets requirements: 8 characters, number, capital, special character

        // Create user if email not in use already

        // Add user to repository

    }

    @PostMapping("/signout")
    public ResponseEntity<String> signout()
    {

    }
}
