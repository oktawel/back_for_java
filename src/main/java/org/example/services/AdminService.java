package org.example.services;


import org.example.models.*;
import org.example.models.DTO.GroupDTO;
import org.example.models.DTO.LecturerDTO;
import org.example.models.DTO.StudentDTO;
import org.example.models.forms.*;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    boolean create_updateLecturer(AddFormLecturer form);
    boolean deleteLecturer (Long id);
    LecturerDTO getLecturerById(Long id);
    List<LecturerDTO> getLecturersByName(String name);
    List<LecturerDTO> getLecturersBySurname(String surname);
    List<LecturerDTO> getAllLecturers();


    boolean create_updateStudent(AddFormStudent form);
    boolean deleteStudent (Long id);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getStudentsByName(String name);
    List<StudentDTO> getStudentsBySurname(String surname);
    List<StudentDTO> getStudentsByGroup(Long groupId);
    List<StudentDTO> getAllStudents();


    boolean create_updateGrooup(AddFormGrooup form);
    boolean deleteGrooup (Long id);
    GroupDTO getGrooupById(Long id);
    List<GroupDTO> getGrooupsByName(String name);
    List<GroupDTO> getAllGrooups();


}
