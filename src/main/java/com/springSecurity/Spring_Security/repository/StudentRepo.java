package com.springSecurity.Spring_Security.repository;

import com.springSecurity.Spring_Security.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
}
