package com.killerexpertise.student.library.service;

import com.killerexpertise.student.library.model.Student;

import java.util.List;

public interface StudentServiceI {
    void createStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);

    List<Student> getAllStudents();

    Student getStudentById(int id);
}
