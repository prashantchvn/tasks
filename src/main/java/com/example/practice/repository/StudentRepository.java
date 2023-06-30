package com.example.practice.repository;

import com.example.practice.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
