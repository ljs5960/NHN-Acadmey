package com.nhnacademy.score.repository;

import com.nhnacademy.score.domain.Student;
import com.nhnacademy.score.exception.StudentAlreadyExistException;
import com.nhnacademy.score.exception.StudentNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(String name) {
        return studentMap.containsKey(name);
    }

    @Override
    public Student getStudent(String name) {
        return exists(name) ? studentMap.get(name) : null;
    }

    @Override
    public Student addStudent(String name, String email, int score, String comment) {
        if (exists(name)) {
            throw new StudentAlreadyExistException();
        }

        Student student = Student.create(name, email, score, comment);

        student.setName(name);
        studentMap.put(name, student);

        return student;
    }

    @Override
    public void modifyStudent(Student student) {
        Student dbStudent = getStudent(student.getName());
        if (Objects.isNull(dbStudent)) {
            throw new StudentNotFoundException();
        }

        dbStudent.setName(student.getName());
        dbStudent.setEmail(student.getEmail());
        dbStudent.setScore(student.getScore());
        dbStudent.setComment(student.getComment());
    }

    @Override
    public void modifyStudent(String name, String email, int score, String comment,
                                 Student targetStudent) {
        if (Objects.isNull(targetStudent)) {
            throw new StudentNotFoundException();
        }

        targetStudent.setName(name);
        targetStudent.setEmail(email);
        targetStudent.setScore(score);
        targetStudent.setComment(comment);
    }
}

