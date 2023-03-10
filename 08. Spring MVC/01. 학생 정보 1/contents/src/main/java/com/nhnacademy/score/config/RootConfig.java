package com.nhnacademy.score.config;

import com.nhnacademy.score.Base;
import com.nhnacademy.score.repository.StudentRepository;
import com.nhnacademy.score.repository.StudentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public StudentRepository studentRepository() {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        studentRepository.addStudent("김학생", "kim.student@nhnacademy.com", 100, "훌륭");

        return studentRepository;
    }
}

