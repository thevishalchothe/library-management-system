package com.killerexpertise.student.library.repository;

import com.killerexpertise.student.library.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Find students by emailId
    List<Student> findByEmailId(String emailId);
}
