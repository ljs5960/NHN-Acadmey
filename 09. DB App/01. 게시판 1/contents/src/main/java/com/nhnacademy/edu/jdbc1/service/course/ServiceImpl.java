package com.nhnacademy.edu.jdbc1.service.course;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;
import com.nhnacademy.edu.jdbc1.repository.Repository;
import com.nhnacademy.edu.jdbc1.repository.RepositoryImpl;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private final Repository repository;

    public ServiceImpl(RepositoryImpl courseRepository) {
        this.repository = courseRepository;
    }

    public List<Course> getCourses() {
        return repository.findAll();
    }

    @Override
    public void deleteCourse(Long courseId) {
        repository.delete(courseId);
    }

    @Override
    public Teacher getTeacher(String teacherName) {
        return (Teacher) repository.findTeacherByName(teacherName);
    }

    @Override
    public Subject getSubject(String subjectName) {
        return (Subject) repository.findSubjectByName(subjectName);
    }

    @Override
    public boolean insertCourse(Course course) {
        return repository.insert(course);
    }

    @Override
    public Course getCourse(Long courseId) {
        return (Course) repository.findCourseById(courseId);
    }

    @Override
    public boolean updateCourse(Course course) {
        return repository.update(course);
    }
}
