package guru.springframework.beerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {

        List<String> err = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(error -> err.add(error.toString()));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
