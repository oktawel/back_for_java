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
            System.out.println("ВОТ ТУТ ВСЁ ПЛОХО 0");
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            System.out.println("ВОТ ТУТ ВСЁ ПЛОХО 1");
            final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            System.out.println("ВОТ ТУТ ВСЁ ПЛОХО 2");
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        }
        catch (BadCredentialsException ex){
            System.out.println(ex);
            return ResponseEntity.ok("Неверные данные пользователя");
        }
        catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.ok("Что-то пошло не так");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Claims claims = jwtTokenUtil.decodeJwt(token);
        return "Token is valid. User: " + claims.getSubject();
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Claims claims = jwtTokenUtil.decodeJwt(token);
        String username = claims.getSubject();
        Integer userId = (Integer) claims.get("userId");
        String role = (String) claims.get("role");

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userId.longValue());
        userInfoDTO.setLogin(username);
        userInfoDTO.setRole(role);
        switch (role){
            case ("Admin"):{
                userInfoDTO.setName(null);
                userInfoDTO.setSurname(null);
                break;
            }
            case ("Lector"):{
                Lecturer lecturer = userDetailsService.findLecturerByUserId(userId);
                userInfoDTO.setName(lecturer.getName());
                userInfoDTO.setSurname(lecturer.getSurname());
                break;
            }
            case ("Student"):{
                Student student = userDetailsService.findStudentByUserId(userId);
                userInfoDTO.setName(student.getName());
                userInfoDTO.setSurname(student.getSurname());
                break;
            }
        }
        return ResponseEntity.ok(userInfoDTO);
        //return "Hello, " + username + ". Your user id is: " + userId + " and your role is: " + role;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Дополнительные операции при выходе пользователя, если необходимо
        return ResponseEntity.ok().build();
    }
}
