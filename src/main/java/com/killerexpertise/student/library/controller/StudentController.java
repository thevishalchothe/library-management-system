package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Student;
import com.killerexpertise.student.library.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentServiceI studentServiceI;

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentServiceI.createStudent(student);
        return new ResponseEntity<>("Student added.", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentServiceI.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentServiceI.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        studentServiceI.updateStudent(student);
        return new ResponseEntity<>("Student updated.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<String> deleteStudent(@RequestParam int id) {
        studentServiceI.deleteStudent(id);
        return new ResponseEntity<>("Student deleted.", HttpStatus.OK);
    }
}
