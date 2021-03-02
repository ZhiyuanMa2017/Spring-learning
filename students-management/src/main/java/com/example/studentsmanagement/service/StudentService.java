package com.example.studentsmanagement.service;

import com.example.studentsmanagement.Dao.StudentDao;
import com.example.studentsmanagement.Dao.UniversityClassDao;
import com.example.studentsmanagement.exceptions.InvalidUniversityClassException;
import com.example.studentsmanagement.exceptions.StudentEmptyNameException;
import com.example.studentsmanagement.exceptions.StudentNonExistException;
import com.example.studentsmanagement.model.Student;
import com.example.studentsmanagement.model.UniversityClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;
    private UniversityClassDao universityClassDao;

    public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
    }

    public Student addStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw new StudentEmptyNameException("Student name cannot be empty");
        }
        return studentDao.save(student);
    }

    public Student updateStudent(Student student) {
        if (student.getId() == null || !studentDao.existsById(student.getId())) {
            throw new StudentNonExistException("cannot find student Id");
        }
        return studentDao.save(student);
    }

    public Student assignClass(Long studentId, Long classId) {
        if (!studentDao.existsById(studentId)) {
            throw new StudentNonExistException("cannot find student Id" + studentId);
        }

        if (!universityClassDao.existsById(classId)) {
            throw new InvalidUniversityClassException("cannot find class Id" + classId);
        }

        Student student = getStudentById(studentId).get();
        UniversityClass universityClass = universityClassDao.findById(classId).get();

        student.setUniversityClass(universityClass);
        return studentDao.save(student);

    }

    public List<Student> getALLStudents() {
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }
}
