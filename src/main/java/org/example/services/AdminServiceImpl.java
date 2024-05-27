package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.models.*;
import org.example.models.DTO.*;
import org.example.models.forms.*;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TestService testService;

    @PostConstruct
    public void init() {
        System.out.println("CourseRepository: " + courseRepository);
        System.out.println("StudentRepository: " + studentRepository);
        System.out.println("LecturerRepository: " + lecturerRepository);
        System.out.println("GroupRepository: " + groupRepository);
        System.out.println("UserRepository: " + userRepository);
        System.out.println("RoleRepository: " + roleRepository);
    }

    private Users saveUser(String login, String password, Role role) {
        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        try {
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw e;
        }
    }
    private Users updateUser(Long id, String login, String password, Role role) {
        Users user = new Users();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        try {
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw e;
        }
    }
    private boolean saveLecturer(Lecturer lecturer) {
        try {
            lecturerRepository.save(lecturer);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private boolean saveGroup(Grooup grooup) {
        try {
            groupRepository.save(grooup);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private boolean saveStudent(Student student) {
        try {
            studentRepository.save(student);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public List<LecturerDTO> getLecturersByName(String name){
        List<Lecturer> lecturers = lecturerRepository.findByName(name);
        return initializeLecturerDTOs(lecturers);
    }
    @Override
    public List<LecturerDTO> getLecturersBySurname(String surname){
        List<Lecturer> lecturers = lecturerRepository.findBySurname(surname);
        return initializeLecturerDTOs(lecturers);
    }
    @Override
    public List<StudentDTO> getStudentsByName(String name){
        List<Student> students = studentRepository.findByName(name);
        return initializeStudentDTOs(students);
    }
    @Override
    public List<StudentDTO> getStudentsBySurname(String surname){
        List<Student> students = studentRepository.findBySurname(surname);
        return initializeStudentDTOs(students);
    }
    @Override
    public List<StudentDTO> getStudentsByGroup(Long groupId){
        List<Student> students = studentRepository.findByGroupId(groupId);
        return initializeStudentDTOs(students);
    }
    @Override
    public List<GroupDTO> getGrooupsByName(String name){
        List<Grooup> grooups = groupRepository.findByName(name);
        return initializeGroupDTOs(grooups);
    }





    @Override
    public boolean create_updateLecturer(AddFormLecturer form){
        String login = (form.getLogin() == null) ? generateLogin() : form.getLogin();
        String password = (form.getPassword() == null) ? generatePassword() : form.getPassword();
        String name = form.getName();
        String surname = form.getSurname();
        Long id;

        Optional<Role> role = roleRepository.findById(2L);
        if (!role.isPresent()) {
            return false;
        }

        Lecturer lecturer = new Lecturer();
        Users user;
        if(form.getId() != null){
            id = form.getId();
            lecturer.setId(id);
            try{
                user = updateUser(lecturerRepository.findById(id).get().getUser().getId() ,login, password, role.get());
            }
            catch (Exception e){
                return false;
            }
        }
        else {
            try{
                user = saveUser(login, password, role.get());
            }
            catch (Exception e){
                return false;
            }
        }

        lecturer.setName(name);
        lecturer.setSurname(surname);
        lecturer.setUser(user);

        if (saveLecturer(lecturer)) {
            return false;
        }

        return true;
    }
    @Override
    public boolean deleteLecturer (Long id){
        try {
            userRepository.deleteById(lecturerRepository.findById(id).get().getUser().getId());
            List<Subject> courses = courseRepository.findByLecturerId(id);
            if (!(courses.isEmpty())) {
                for (Subject course : courses) {
                    course.setLecturer(null);
                    courseRepository.save(course);
                }
            }
            lecturerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public LecturerDTO getLecturerById(Long id){
        return initializeLecturerDTO(lecturerRepository.findById(id).get()) ;
    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        return initializeLecturerDTOs(lecturers);
    }

    @Override
    public boolean create_updateGrooup(AddFormGrooup form){
        Grooup group = new Grooup();
        if(form.getId() != null){
            group.setId(form.getId());
        }
        group.setName(form.getName());
        if (saveGroup(group)) {
            return false;
        }
        return true;
    }
    @Override
    public boolean deleteGrooup (Long id){
        try {
            Optional<Grooup> groupOptional = groupRepository.findById(id);
            if (groupOptional.isPresent()) {
                Grooup group = groupOptional.get();
                for (Subject course : group.getSubjects()) {
                    course.getGroups().remove(group);
                    courseRepository.save(course);
                }
                for (Student student: studentRepository.findByGroupId(group.getId())){
                    student.setGroup(null);
                }
                groupRepository.delete(group);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public GroupDTO getGrooupById(Long id){
        return initializeGroupDTO(groupRepository.findById(id).get());
    }
    @Override
    public List<GroupDTO> getAllGrooups(){
        List<Grooup> grooups = groupRepository.findAll();
        return initializeGroupDTOs(grooups);
    }

    @Override
    public boolean create_updateStudent(AddFormStudent form){
        String login = (form.getLogin() == null) ? generateLogin() : form.getLogin();
        String password = (form.getPassword() == null) ? generatePassword() : form.getPassword();
        String name = form.getName();
        String surname = form.getSurname();
        Date birthDate = form.getBirthDate();
        Long groupId = form.getGroupId();
        Long id;

        Optional<Role> role = roleRepository.findById(3L);
        if (!role.isPresent()) {
            return false;
        }

        Student student = new Student();
        Users user;
        if(form.getId() != null){
            id = form.getId();
            student.setId(id);
            try{
                user = updateUser(studentRepository.findById(id).get().getUser().getId() ,login, password, role.get());
            }
            catch (Exception e){
                return false;
            }
        }
        else {
            try{
                user = saveUser(login, password, role.get());
            }
            catch (Exception e){
                return false;
            }
        }

        student.setName(name);
        student.setSurname(surname);
        student.setBirthDate(birthDate);
        student.setGroup(groupRepository.findById(groupId).get());
        student.setUser(user);
        if (saveStudent(student)) {
            return false;
        }

        return true;
    }
    @Override
    public boolean deleteStudent (Long id){
        try {
            userRepository.deleteById(studentRepository.findById(id).get().getUser().getId());
            testService.deleteRestultTest(id);
            studentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public StudentDTO getStudentById(Long id){
        return initializeStudentDTO(studentRepository.findById(id).get());
    }
    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return initializeStudentDTOs(students);
    }

    private GroupDTO initializeGroupDTO(Grooup group) {
        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());

        Set<CourseDTOForGroup> courseDTOs = group.getSubjects().stream()
                .map(this::convertToCourseDTO)
                .collect(Collectors.toSet());
        dto.setCourses(courseDTOs);

        return dto;
    }

    private CourseDTOForGroup convertToCourseDTO(Subject subject) {
        CourseDTOForGroup dto = new CourseDTOForGroup();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setDescription(subject.getDescription());
        dto.setLecturerName(subject.getLecturer().getName());
        dto.setLecturerSurname(subject.getLecturer().getSurname());
        return dto;
    }

    private LecturerDTO initializeLecturerDTO(Lecturer lecturer) {
        LecturerDTO dto = new LecturerDTO();
        dto.setId(lecturer.getId());
        dto.setName(lecturer.getName());
        dto.setSurname(lecturer.getSurname());
        dto.setUserLogin(lecturer.getUser().getLogin());
        dto.setUserPassword(lecturer.getUser().getPassword());
        return dto;
    }
    private StudentDTO initializeStudentDTO(Student student) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        dto.setGroup(student.getGroup().getName());

        String birthDateStr = dateFormat.format(student.getBirthDate());
        dto.setBirthDate(birthDateStr);

        dto.setUserLogin(student.getUser().getLogin());
        dto.setUserPassword(student.getUser().getPassword());
        return dto;
    }

    private List<GroupDTO> initializeGroupDTOs(List<Grooup> groups) {
        List<GroupDTO> groupDTOs = new ArrayList<>();
        for (Grooup group : groups) {
            groupDTOs.add(initializeGroupDTO(group));
        }
        return groupDTOs;
    }

    private List<LecturerDTO> initializeLecturerDTOs(List<Lecturer> lecturers) {
        List<LecturerDTO> lecturerDTOs = new ArrayList<>();
        for (Lecturer lecturer : lecturers) {
            lecturerDTOs.add(initializeLecturerDTO(lecturer));
        }
        return lecturerDTOs;
    }

    private List<StudentDTO> initializeStudentDTOs(List<Student> students) {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(initializeStudentDTO(student));
        }
        return studentDTOs;
    }





    private String generatePassword (){
        int lenght = 7;
        String characters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lenght; i++ ){
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    private String generateLogin (){
        int lenght = 8;
        String characters = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        StringBuilder login = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lenght; i++ ){
            int index = random.nextInt(characters.length());
            login.append(characters.charAt(index));
        }
        return login.toString();
    }
}
