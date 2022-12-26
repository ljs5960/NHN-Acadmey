package com.nhnacademy.edu.jdbc1.service.course;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;

import java.util.List;

public interface Service {
    List<Course> getCourses();

    Teacher getTeacher(String teacherName);

    Subject getSubject(String subjectName);

    boolean insertCourse(Course course);

    Course getCourse(Long courseId);

    boolean updateCourse(Course course);

    void deleteCourse(Long courseId);
}
