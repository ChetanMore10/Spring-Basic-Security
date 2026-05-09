package com.springSecurity.Spring_Security.controller;

import com.springSecurity.Spring_Security.entity.Student;
import com.springSecurity.Spring_Security.entity.User;
import com.springSecurity.Spring_Security.repository.UserRepo;
import com.springSecurity.Spring_Security.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // HOME PAGE
    @GetMapping("/")
    public String homePage() {

        return "home";
    }

    // ADMIN PAGE
    @GetMapping("/admin")
    public String adminPage() {

        return "admin";
    }

    // USER PAGE
    @GetMapping("/user")
    public String userPage() {

        return "user";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute(
                "user",
                new User());

        return "register";
    }

    // SAVE USER
    @PostMapping("/saveUser")
    public String saveUser(User user, Model model) {
        User existingUser = userRepo.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null) {
            model.addAttribute("error", "Username already exists!");
            model.addAttribute("user", new User());
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/login";
    }

    // STUDENT LIST
    @GetMapping("/students")
    public String getStudents(Model model) {

        model.addAttribute(
                "students",
                studentService.getAllStudent());

        return "students";
    }

    // ADD STUDENT PAGE
    @GetMapping("/addStudent")
    public String addStudentPage(Model model) {

        model.addAttribute(
                "students",
                new Student());

        return "addStudent";
    }

    // SAVE STUDENT
    @PostMapping("/saveStudent")
    public String saveStudent(Student student) {

        studentService.addStudent(student);

        return "redirect:/students";
    }

    // EDIT STUDENT PAGE
    @GetMapping("/edit/{id}")
    public String editStudent(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "student",
                studentService.getStudentById(id));

        return "updateStudent";
    }

    // UPDATE STUDENT
    @PostMapping("/updateStudent")
    public String updateStudent(Student student) {

        studentService.updateStud(
                student.getId(),
                student);

        return "redirect:/students";
    }

    // DELETE STUDENT
    @GetMapping("/delete/{id}")
    public String deleteStudent(
            @PathVariable Integer id) {

        studentService.deleteStud(id);

        return "redirect:/students";
    }
}