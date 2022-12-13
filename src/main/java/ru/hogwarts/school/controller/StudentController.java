package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
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
        if (student.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must by empty");
        }
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student newStudent = studentService.findStudent(student.getId());
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
    public ResponseEntity<Collection<Student>> getAllStudentsByAgeBetween(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "minAge", required = false) Integer minAge,
            @RequestParam(value = "maxAge", required = false) Integer maxAge) {
        Collection<Student> students;
        if (age != null) {
            students = studentService.getAllStudentEqualsAge(age);
        } else if (minAge != null && maxAge != null) {
            students = studentService.getAgeBetween(minAge, maxAge);
        } else {
            students = studentService.getAllStudents();
        }
        return ResponseEntity.ok(Objects.requireNonNullElse(students, Collections.emptyList()));
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> getFacultyStudent(@PathVariable long id) {
        Faculty faculty = studentService.findFaculty(id);
        return ResponseEntity.ok(faculty);
    }



    @GetMapping("/count-student")
    public int getCountStudent() {
        return studentService.getCountStudent();
    }

    @GetMapping("/avg-age-student")
    public int getAvgAgeStudent() {
        return studentService.getAvgAgeStudent();
    }

    @GetMapping("/count-student-number/{number}")
    public ResponseEntity<Collection<Student>> getCountStudentNumber(@PathVariable int number) {
        return ResponseEntity.ok(studentService.getCountStudentNumber(number));
    }
}
