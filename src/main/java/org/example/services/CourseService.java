package org.example.services;

import org.example.models.DTO.CourseDTO;
import org.example.models.forms.AddFormCourse;
import org.example.models.forms.UpdateFormCourse;

import java.util.List;

public interface CourseService {

    boolean create_updateCourse(AddFormCourse form);
    //boolean updateCourse(UpdateFormCourse form);
    boolean deleteCourse(Long id);
    CourseDTO getCourseById(Long id);
    List<CourseDTO> getAllCourses();

}
