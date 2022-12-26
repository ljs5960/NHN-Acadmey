package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.config.DatabaseConfig;
import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class RepositoryImpl implements Repository {
    DatabaseConfig databaseConfig = new DatabaseConfig();
    DataSource dataSource = databaseConfig.dataSource();

    @Override
    public List<Course> findAll() {
        List<Course> courseList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "c.id as c_id, c.created_at as c_created_at,\n" +
                "t.id as t_id, t.name as t_name, t.created_at as t_created_at,\n" +
                "s.id as s_id, s.name as s_name, s.created_at as s_created_at\n" +
                "FROM JdbcCourses as c\n" +
                "\tINNER JOIN JdbcTeachers as t ON c.teacher_id=t.id\n" +
                "    INNER JOIN JdbcSubjects as s ON c.subject_id = s.id;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getLong("t_id"), rs.getString("t_name"));
                Subject subject = new Subject(rs.getLong("s_id"), rs.getString("s_name"));
                courseList.add(new Course(rs.getLong("c_id"), teacher, subject, rs.getTimestamp("c_created_at")));
            }

            return courseList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Teacher findTeacherByName(String name) {
        String SQL = "SELECT * FROM JdbcTeachers WHERE name=?";
        Teacher teacher = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                teacher = new Teacher(rs.getLong("id"), rs.getString("name"));
            }

            return teacher;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject findSubjectByName(String name) {
        String SQL = "SELECT * FROM JdbcSubjects WHERE name=?";
        Subject subject = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                subject = new Subject(rs.getLong("id"), rs.getString("name"));
            }

            return subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Course course) {
        String SQL =
                "INSERT INTO JdbcCourses(teacher_id, subject_id, created_at)\n" +
                "VALUES (?, ?, ?);";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, course.getTeacher().getId());
            statement.setLong(2, course.getSubject().getId());
            statement.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findCourseById(Long courseId) {
        String SQL = "SELECT\n" +
                "c.id as c_id, c.created_at as c_created_at,\n" +
                "t.id as t_id, t.name as t_name, t.created_at as t_created_at,\n" +
                "s.id as s_id, s.name as s_name, s.created_at as s_created_at\n" +
                "FROM JdbcCourses as c\n" +
                "\tINNER JOIN JdbcTeachers as t ON c.teacher_id=t.id\n" +
                "    INNER JOIN JdbcSubjects as s ON c.subject_id = s.id;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            ResultSet rs = statement.executeQuery();
            Course course = null;

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getLong("t_id"), rs.getString("t_name"));
                Subject subject = new Subject(rs.getLong("s_id"), rs.getString("s_name"));
                new Course(rs.getLong("c_id"), teacher, subject, rs.getTimestamp("c_created_at"));
            }
            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Course course) {
        String SQL = "UPDATE JdbcCourses\n" +
                "SET teacher_id=?, subject_id=?\n" +
                "WHERE id=?;";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, course.getTeacher().getId());
            statement.setLong(2, course.getSubject().getId());
            statement.setLong(3, course.getId());
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long courseId) {
        String SQL = "DELETE\n" +
                "FROM JdbcCourses\n" +
                "WHERE id=?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
