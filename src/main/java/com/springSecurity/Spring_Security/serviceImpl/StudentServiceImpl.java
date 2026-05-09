package com.springSecurity.Spring_Security.serviceImpl;

import com.springSecurity.Spring_Security.entity.Student;
import com.springSecurity.Spring_Security.repository.StudentRepo;
import com.springSecurity.Spring_Security.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student updateStud(Integer id, Student student) {
        Student exesting = studentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found with id : "+id));
        exesting.setName(student.getName());
        exesting.setAge(student.getAge());
        exesting.setAddress(student.getAddress());
        exesting.setGender(student.getGender());
        return studentRepo.save(exesting);
    }

    @Override
    public String deleteStud(Integer id) {
        Student deleted = studentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Student Not Found..!"));
        studentRepo.delete(deleted);
        return "Student Deleted Successfully...!";
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Student Not Found With ID : " + id));
    }
}