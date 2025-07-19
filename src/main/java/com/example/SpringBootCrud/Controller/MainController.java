package com.example.SpringBootCrud.Controller;


import com.example.SpringBootCrud.Model.Student;
import com.example.SpringBootCrud.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @GetMapping("/fetchStudent")
    public List<Student> fetchStudents() {
        return studentRepo.findAll();
    }

    @GetMapping("/getStudent/{rollNo}")
    public Student getStudentByRollNo(@PathVariable Integer rollNo) {
        return studentRepo.findByRollNo(rollNo).orElse(null);
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        Student existingStudent = studentRepo.findByRollNo(student.getRollNo()).orElse(null);

        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setDepartment(student.getDepartment());
            return studentRepo.save(existingStudent);
        }

        return null;
    }


    @DeleteMapping("/deleteStudent/{rollNo}")
    public String deleteStudent(@PathVariable Integer rollNo) {
        Optional<Student> student = studentRepo.findByRollNo(rollNo);
        if (student.isPresent()) {
            studentRepo.delete(student.get());
            return "Student deleted";
        }
        return "Student not found";
    }
}
