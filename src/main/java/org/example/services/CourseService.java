package org.example.services;

import org.example.models.forms.AddFormCourse;

public interface CourseService {

    boolean create_updateCourse(AddFormCourse form);
    boolean deleteCourse(Long id);
    CourseDTO getCourseById(Long id);
    List<CourseDTO> getAllCourses();

}
