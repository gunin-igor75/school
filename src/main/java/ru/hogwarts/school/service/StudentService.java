package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        Collection<Student> collection = studentRepository.findAll();
        System.out.println(collection);
        return  collection;
    }

    public Collection<Student> getAllStudentEqualsAge(int age) {
        return studentRepository.findFacultyAge(age);
    }

    public Collection<Student> getAgeBetween(Integer minAge, Integer maxAge) {
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Faculty findFaculty(long id) {
        Student student = findStudent(id);
        return student.getFaculty();
    }
}
