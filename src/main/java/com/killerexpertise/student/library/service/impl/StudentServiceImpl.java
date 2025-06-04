package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.model.Card;
import com.killerexpertise.student.library.model.Student;
import com.killerexpertise.student.library.repository.StudentRepository;
import com.killerexpertise.student.library.service.CardServiceI;
import com.killerexpertise.student.library.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentServiceI {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardServiceI cardServiceI;

    @Override
    public void createStudent(Student student) {
        // Save student first
        Student savedStudent = studentRepository.save(student);
        
        // Then create card for student
        Card card = cardServiceI.createCard(savedStudent);
        
        // Assuming cardService creates and assigns the card to the student internally
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student); // JPA save handles update based on ID
    }

    @Override
    public void deleteStudent(int id) {
        cardServiceI.deactivate(id);      // Deactivate the card before deleting student
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> optional = studentRepository.findById(id);
        return optional.orElse(null);
    }
}
