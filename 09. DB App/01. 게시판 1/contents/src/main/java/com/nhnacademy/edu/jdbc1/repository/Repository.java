package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;

import java.util.List;

public interface Repository {
    List<Course> findAll();

    Teacher findTeacherByName(String name);

    Subject findSubjectByName(String name);

    boolean insert(Course course);

    Course findCourseById(Long courseId);

    boolean update(Course course);

    void delete(Long courseId);
}
