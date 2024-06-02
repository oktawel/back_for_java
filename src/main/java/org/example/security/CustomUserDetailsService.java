package org.example.security;

import io.jsonwebtoken.Claims;
import org.example.models.DTO.UserDTO;
import org.example.models.DTO.UserInfoDTO;
import org.example.models.Lecturer;
import org.example.models.Role;
import org.example.models.Student;
import org.example.models.Users;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    @Autowired
    public CustomUserDetailsService(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    public UserInfoDTO getUserInformation(Claims claims){
        try {
            String username = claims.getSubject();
            Integer userId = (Integer) claims.get("userId");
            String role = (String) claims.get("role");

            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setUserId(userId.longValue());
            userInfoDTO.setLogin(username);
            userInfoDTO.setRole(role);
            switch (role){
                case ("Admin"):{
                    userInfoDTO.setId(null);
                    userInfoDTO.setName(null);
                    userInfoDTO.setSurname(null);
                    break;
                }
                case ("Lector"):{
                    Lecturer lecturer = userService.findLecturerByUserId(userId.longValue());
                    userInfoDTO.setId(lecturer.getId());
                    userInfoDTO.setName(lecturer.getName());
                    userInfoDTO.setSurname(lecturer.getSurname());
                    break;
                }
                case ("Student"):{
                    Student student = userService.findStudentByUserId(userId.longValue());
                    userInfoDTO.setId(student.getId());
                    userInfoDTO.setName(student.getName());
                    userInfoDTO.setSurname(student.getSurname());
                    break;
                }
            }

            return userInfoDTO;
        }
        catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByLogin(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Users user = new Users();
        user.setLogin(userDTO.getUserLogin());
        user.setPassword("{noop}" + userDTO.getUserPassword());
        user.setRole(roleRepository.findByName(userDTO.getRoleName()));
        user.setId(userDTO.getId());

        return new CustomUserDetails(user);

    }

}
