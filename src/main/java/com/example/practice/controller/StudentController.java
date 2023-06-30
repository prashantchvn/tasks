package com.example.practice.controller;

import com.example.practice.entities.Student;
import com.example.practice.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository repo;

    @PostMapping("/")
    public String saveStudent(@RequestBody Student std){
        repo.save(std);
        return "Student added successfully";
    }
    @GetMapping("/")
    public List<Student> getStudents(){
        return repo.findAll();
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable("id") String id,@RequestBody Student std){
        std.setId(id);
        return repo.save(std);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id){
        repo.deleteById(id);
        return "Student Deleted ";
    }
}
