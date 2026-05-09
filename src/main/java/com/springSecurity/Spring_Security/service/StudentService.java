package com.springSecurity.Spring_Security.service;

import com.springSecurity.Spring_Security.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudent();
    Student addStudent(Student student);
    Student updateStud(Integer id, Student student);
    String deleteStud(Integer id);
    Student getStudentById(Integer id);
}
