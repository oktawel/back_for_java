package org.example.Controllers;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.DTO.UserInfoDTO;
import org.example.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            final CustomUserDetails userDetails = userDetailsService.loadUserByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
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

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            if (tokenBlacklistService.isTokenInvalid(token)) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Token is invalid.");
            }
            Claims claims = jwtTokenUtil.decodeJwt(token);
            UserInfoDTO userInfoDTO = userDetailsService.getUserInformation(claims);
            if (userInfoDTO == null){
                return ResponseEntity.badRequest().body("User is not found");
            } else {
                return ResponseEntity.ok(userInfoDTO);
            }
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        tokenBlacklistService.invalidateToken(token);
        System.out.println(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
