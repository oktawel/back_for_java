package org.example.Controllers;


import io.jsonwebtoken.Claims;
import org.example.models.DTO.UserInfoDTO;
import org.example.models.Lecturer;
import org.example.models.Student;
import org.example.models.Users;
import org.example.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try {
//            System.out.println("ВОТ ТУТ ВСЁ ПЛОХО 0");
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
//            System.out.println("ВОТ ТУТ ВСЁ ПЛОХО 1");
            final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//            System.out.println("ВОТ ТУТ ВСЁ ПЛОХО 2");
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        }
        catch (BadCredentialsException ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Claims claims = jwtTokenUtil.decodeJwt(token);
        return ResponseEntity.ok("Token is valid.");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Claims claims = jwtTokenUtil.decodeJwt(token);
        UserInfoDTO userInfoDTO = userDetailsService.getUserInformation(claims);
        if (userInfoDTO == null){
            return ResponseEntity.badRequest().body("User is not found");
        }
        else {
            return ResponseEntity.ok(userInfoDTO);
        }
        //return "Hello, " + username + ". Your user id is: " + userId + " and your role is: " + role;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Дополнительные операции при выходе пользователя, если необходимо
        return ResponseEntity.ok().build();
    }
}
