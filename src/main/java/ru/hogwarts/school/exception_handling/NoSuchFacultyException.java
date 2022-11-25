package ru.hogwarts.school.exception_handling;

public class NoSuchFacultyException extends RuntimeException{
    public NoSuchFacultyException(String message) {
        super(message);
    }
}
