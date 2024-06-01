package org.example.security;

import org.example.models.DTO.UserDTO;
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

    public Lecturer findLecturerByUserId(Integer id) {
        return userService.findLecturerByUserId(id.longValue());
    }
    public Student findStudentByUserId(Integer id) {
        return userService.findStudentByUserId(id.longValue());
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
