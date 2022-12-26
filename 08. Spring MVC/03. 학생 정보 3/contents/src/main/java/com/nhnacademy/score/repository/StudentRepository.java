package com.nhnacademy.score.repository;

import com.nhnacademy.score.domain.Student;

public interface StudentRepository {
    boolean exists(String name);

    Student getStudent(String name);

    Student addStudent(String name, String email, int score, String comment);

    void modifyStudent(Student student);

    void modifyStudent(String name, String email, int score, String comment, Student targetStudent);
}

