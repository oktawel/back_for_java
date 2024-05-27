package org.example.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/user")
    public String userAccess(Authentication authentication) {
        return "Welcome, " + authentication.getName();
    }

    @GetMapping("/admin")
    public String adminAccess(Authentication authentication) {
        return "Welcome Admin, " + authentication.getName();
    }
}
