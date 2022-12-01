package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
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
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
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
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
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
            @RequestParam(value = "colorName", required = false) String colorName){
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
