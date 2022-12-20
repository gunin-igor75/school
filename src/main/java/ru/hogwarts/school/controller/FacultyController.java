package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.exception_handling.NoSuchFacultyException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        if (faculty.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must by empty");
        }
        Faculty newFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFaculty);
    }

    @GetMapping("{id}")
    public Faculty findFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            throw new NoSuchFacultyException("There is no faculty with ID = " + id +
                    " in Database");
        }
        return faculty;
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.findFaculty(faculty.getId());
        if (newFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facultyService.editFaculty(newFaculty));
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            throw new NoSuchFacultyException("There is no faculty with ID = " + id +
                    " in Database");
        }
        facultyService.deleteFaculty(id);
        return faculty;
    }

    @GetMapping
    public Collection<Faculty> getAllFaculty() {
        Collection<Faculty> faculties = facultyService.getAllFaculty();
        if (faculties == null) {
            return Collections.emptyList();
        }
        return faculties;
    }

    @GetMapping(params = "colorName")
    public Faculty getAllFacultyByColorOrName(
            @RequestParam(value = "colorName", required = false) String colorName) {
        Faculty faculty = facultyService.findFacultyByColorName(colorName);
        if (faculty == null) {
            throw new NoSuchFacultyException("There is no faculty with value = " + colorName +
                    " in Database");
        }
        return faculty;
    }

    @GetMapping("/student{id}")
    public Collection<Student> getStudentFaculty(@PathVariable long id) {
        return facultyService.findStudents(id);
    }

}
