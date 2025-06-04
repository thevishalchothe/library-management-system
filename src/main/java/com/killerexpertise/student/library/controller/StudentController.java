package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Student;
import com.killerexpertise.student.library.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServiceI studentServiceI;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        studentServiceI.createStudent(student);
        return "Student added.";
    }

    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student) {
        studentServiceI.updateStudent(student);
        return "Student updated.";
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id) {
        studentServiceI.deleteStudent(id);
        return "Student deleted.";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentServiceI.getAllStudents();
    }

    @GetMapping("/getStudent/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentServiceI.getStudentById(id);
    }

}
