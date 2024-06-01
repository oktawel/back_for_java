package org.example.services;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.GroupDTOForCourse;
import org.example.models.DTO.StudentDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.Grooup;
import org.example.models.Student;
import org.example.models.Subject;
import org.example.models.forms.AddFormCourse;
import org.example.models.forms.UpdateFormCourse;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TestService testService;

    @Override
    public boolean create_updateCourse(AddFormCourse form){
        try {
            Subject subject = new Subject();
            Long id;

            if(form.getId() != null){
                id = form.getId();
                subject.setId(id);
                subject.setGroups(courseRepository.findById(id).get().getGroups());
            }

            subject.setName(form.getName());
            subject.setDescription(form.getDescription());
            subject.setLecturer(lecturerRepository.findById(form.getLecturerId()).get());

            if (saveCourse(subject)) {
                return false;
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean addGroupToCourse(Long courseId, Long groupId) {
        try {
            Subject course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

            Grooup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

            course.getGroups().add(group);
            courseRepository.save(course);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean removeGroupFromCourse(Long courseId, Long groupId) {
        try {
        Subject course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Grooup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        course.getGroups().remove(group);

            courseRepository.save(course);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    @Override
    public boolean deleteCourse(Long id){
        try {
            Optional<Subject> courseOptional = courseRepository.findById(id);
            if (courseOptional.isPresent()) {
                Subject course = courseOptional.get();
                for (Grooup group : course.getGroups()) {
                    group.getSubjects().remove(course);
                    groupRepository.save(group);
                }
                List<TestDTO> tests = testService.getTestsBySubjectId(id);
                for (TestDTO test : tests) {
                    testService.deleteTest(test.getId());
                }
                courseRepository.delete(course);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public CourseDTO getCourseById(Long id){
        return initializeCourseDTO(courseRepository.findById(id).get());
    }
    @Override
    public List<CourseDTO> getAllCourses(){
        List<Subject> subjects = courseRepository.findAll();
        return initializeCourseDTOs(subjects);
    }

    private boolean saveCourse(Subject subject) {
        try {
            courseRepository.save(subject);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    private CourseDTO initializeCourseDTO(Subject course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        if (course.getLecturer() != null){
            dto.setLecturerName(course.getLecturer().getName());
            dto.setLecturerSurname(course.getLecturer().getSurname());
        }
        else{
            dto.setLecturerName(null);
            dto.setLecturerSurname(null);
        }
        Set<GroupDTOForCourse> groupDTOs = course.getGroups().stream()
                .map(this::convertToGroupDTO)
                .collect(Collectors.toSet());
        dto.setGroups(groupDTOs);
        return dto;
    }


    private GroupDTOForCourse convertToGroupDTO(Grooup group) {
        GroupDTOForCourse dto = new GroupDTOForCourse();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }

    private List<CourseDTO> initializeCourseDTOs(List<Subject> courses) {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Subject course : courses) {
            courseDTOs.add(initializeCourseDTO(course));
        }
        return courseDTOs;
    }
}
