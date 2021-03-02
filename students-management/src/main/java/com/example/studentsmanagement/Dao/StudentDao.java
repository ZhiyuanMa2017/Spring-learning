package com.example.studentsmanagement.Dao;

import com.example.studentsmanagement.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Long> {
}
