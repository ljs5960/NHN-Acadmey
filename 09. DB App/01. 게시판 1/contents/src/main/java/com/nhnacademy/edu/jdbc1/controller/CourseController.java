package com.nhnacademy.edu.jdbc1.controller;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;
import com.nhnacademy.edu.jdbc1.service.course.Service;
import com.nhnacademy.edu.jdbc1.service.course.ServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class CourseController {
    private final Service service;

    public CourseController(ServiceImpl serviceImpl) {
        this.service = serviceImpl;
    }

    @GetMapping("/")
    public String getCourse(ModelMap modelMap) {
        List<Course> courseList = service.getCourses();
        modelMap.put("courses", courseList);
        return "index";
    }

    @GetMapping("/insert")
    public String insertCourse() {
        return "insertForm";
    }

    @PostMapping("/insert")
    public String insertCourse(@RequestParam("t_name") String teacherName, @RequestParam("s_name") String subjectName) {
        Teacher teacher = service.getTeacher(teacherName);
        Subject subject = service.getSubject(subjectName);

        if(Objects.isNull(teacher) || Objects.isNull(subject)) {
            return "redirect:/insert";
        }

        Course course = new Course(teacher, subject);
        service.insertCourse(course);

        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateCourse() {
        return "updateForm";
    }

    @PostMapping("/update")
    public String updateCourse(@RequestParam("c_id") Long courseId,
                               @RequestParam("t_name") String teacherName,
                               @RequestParam("s_name") String subjectName,
                               ModelMap modelMap) {
        Teacher teacher = service.getTeacher(teacherName);
        Subject subject = service.getSubject(subjectName);

        if(Objects.isNull(teacher) || Objects.isNull(subject)) {
            return "redirect:/update";
        }

        Course course = new Course(courseId, teacher, subject);
        service.updateCourse(course);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") Long courseId) {
        service.deleteCourse(courseId);

        return "redirect:/";
    }
}
