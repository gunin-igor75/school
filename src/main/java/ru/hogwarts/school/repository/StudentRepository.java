package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository  extends JpaRepository<Student, Long> {
    @Query("select pos from student pos  where pos.age = :age")
    Collection<Student> findFacultyAge(int age);
}
