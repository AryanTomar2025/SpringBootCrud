package com.example.SpringBootCrud.Repository;

import com.example.SpringBootCrud.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepo extends MongoRepository<Student, String> {


    Optional<Student> findByRollNo(Integer rollNo);


}
