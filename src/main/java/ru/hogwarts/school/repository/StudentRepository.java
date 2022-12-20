package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {
    @Query("select pos from student pos  where pos.age = :age")
    Collection<Student> findFacultyAge(Integer age);

    Collection<Student> findAllByAgeBetween(Integer minAge, Integer maxAge);

    @Query(value = "select count(*)  from student", nativeQuery = true)
    int getCountStudents();

    @Query(value = "select round(avg(age)) from  student", nativeQuery = true)
    int getAvgAgeStudent();


    @Query(value = "select * from student order by id desc limit ?1 ", nativeQuery = true)
    Collection<Student> getCountStudentNumber(int num);
}
