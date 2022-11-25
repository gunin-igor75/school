package ru.hogwarts.school.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchoolGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SchoolIncorrectData> handleException(
            NoSuchFacultyException exception) {
        SchoolIncorrectData data = new SchoolIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SchoolIncorrectData> handleException(
            Exception exception) {
        SchoolIncorrectData data = new SchoolIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
