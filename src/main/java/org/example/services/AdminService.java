package org.example.services;


import org.example.models.*;
import org.example.models.DTO.*;
import org.example.models.forAdmin.*;

import java.util.List;
import java.util.Optional;

public interface AdminService {

//    boolean addNewLecturer(AddFormLecturer form);
//    boolean updateLecturer(AddFormLecturer form);
    boolean create_updateLecturer(AddFormLecturer form);
    boolean deleteLecturer (Long id);
    LecturerDTO getLecturerById(Long id);
    List<LecturerDTO> getAllLecturers();



//    boolean addNewStudent(AddFormStudent form);
//    boolean updateStudent(AddFormStudent form);
    boolean create_updateStudent(AddFormStudent form);
    boolean deleteStudent (Long id);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents();



//    boolean addNewGrooup(AddFormGrooup form);
//    boolean updateGrooup(AddFormGrooup form);
    boolean create_updateGrooup(AddFormGrooup form);
    boolean deleteGrooup (Long id);
    Optional<Grooup> getGrooupById(Long id);
    List<Grooup> getAllGrooups();


}
