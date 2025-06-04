package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Student;
import com.killerexpertise.student.library.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServiceI studentServiceI;

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentServiceI.createStudent(student);
        return ResponseEntity.ok("Student added.");
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        studentServiceI.updateStudent(student);
        return ResponseEntity.ok("Student updated.");
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<String> deleteStudent(@RequestParam int id) {
        studentServiceI.deleteStudent(id);
        return ResponseEntity.ok("Student deleted.");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentServiceI.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentServiceI.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
