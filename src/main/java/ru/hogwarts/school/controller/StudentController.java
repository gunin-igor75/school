package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student newStudent = studentService.findStudent(student.getId());
        System.out.println(newStudent);
        if (newStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.editStudent(student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public Collection<Student> getAllStudent() {
        Collection<Student> students = studentService.getAllStudents();
        System.out.println(students);
        return students;
    }

    @GetMapping(params = "age")
    public ResponseEntity<Collection<Student>> getAllStudentsEquals(@RequestParam(value = "age",
            required = false) Integer age) {
        Collection<Student> students = studentService.getAllStudentEqualsAge(age);
        return ResponseEntity.ok(Objects.requireNonNullElse(students, Collections.emptyList()));
    }

    @GetMapping(params = "maiAge, maxAge")
    public ResponseEntity<Collection<Student>> getAllStudentsByAgeBetween(
            @RequestParam(value = "minAge", required = false) Integer minAge,
            @RequestParam(value = "maxAge", required = false) Integer maxAge) {
        Collection<Student> students = studentService.getAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(Objects.requireNonNullElse(students, Collections.emptyList()));
    }

    @GetMapping("/faculty{id}")
    public ResponseEntity<Faculty> getFacultyStudent(@PathVariable long id) {
        Faculty faculty = studentService.findFaculty(id);
        return ResponseEntity.ok(faculty);
    }
}
