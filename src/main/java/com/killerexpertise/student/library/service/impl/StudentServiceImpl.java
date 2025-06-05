package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.exception.StudentNotFoundException;
import com.killerexpertise.student.library.model.Student;
import com.killerexpertise.student.library.repository.StudentRepository;
import com.killerexpertise.student.library.service.CardServiceI;
import com.killerexpertise.student.library.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceI {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardServiceI cardServiceI;

    @Override
    public void createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        cardServiceI.createCard(savedStudent);
    }

    @Override
    public void updateStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            throw new StudentNotFoundException("Student with ID " + student.getId() + " not found for update.");
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found for deletion.");
        }
        cardServiceI.deactivate(id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found."));
    }
}
