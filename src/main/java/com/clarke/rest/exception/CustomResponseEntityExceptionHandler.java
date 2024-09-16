package com.clarke.rest.exception;

import com.clarke.rest.beans.ErrorDetails;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                                    request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) 
                                                                               throws Exception {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                                    request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }    

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
                                            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errors = ex.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(";"));
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errors,
                            request.getDescription(false));
                
             //   .forEach(error -> error.getField());
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
