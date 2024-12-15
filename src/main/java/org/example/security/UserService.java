package org.example.security;

import org.example.models.DTO.LecturerDTO;
import org.example.models.DTO.UserDTO;
import org.example.models.Lecturer;
import org.example.models.Student;
import org.example.models.Users;
import org.example.repository.LecturerRepository;
import org.example.repository.StudentRepository;
import org.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final UsersRepository userRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Autowired
    public UserService(UsersRepository userRepository, StudentRepository studentRepository, LecturerRepository lecturerRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    public Lecturer findLecturerByUserId(Long id) {
        return lecturerRepository.findByUserId(id);
    }

    public Student findStudentByUserId(Long id) {
        return studentRepository.findByUserId(id);
    }


    public UserDTO findByLoginAndPassword(String login, String password) {
        try {
            Optional<Users> user = userRepository.findByLoginAndPassword(login, password);
            System.out.println(user);
            if (user.isEmpty()){return null;}
            else{ return initializeUsersDTO(user.get());}

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public UserDTO findByLogin(String login) {
        try {
            return initializeUsersDTO(userRepository.findByLogin(login));
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    private UserDTO initializeUsersDTO(Users user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserLogin(user.getLogin());
        dto.setUserPassword(user.getPassword());
        dto.setRoleName(user.getRole().getName());
        return dto;
    }
}
