package com.iprody.customerservice.handlers;

import com.iprody.customerservice.dto.errors.ErrorsResponseDto;
import com.iprody.customerservice.dto.errors.ValidationErrorsDto;
import com.iprody.customerservice.exceptions.ResourceNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsResponseDto> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        log.error("Validation failed: {}", ex.getMessage(), ex);
        List<ValidationErrorsDto> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        error ->
                                new ValidationErrorsDto(
                                        error.getField(),
                                        error.getDefaultMessage()
                                )
                )
                .toList();

        ErrorsResponseDto errorsResponseDto = new ErrorsResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );
        return new ResponseEntity<>(errorsResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorsResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex
    ) {
        log.error("Customer not found: {}", ex.getMessage(), ex);
        ErrorsResponseDto errorsResponseDto = new ErrorsResponseDto(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Collections.emptyList()
        );

        return new ResponseEntity<>(errorsResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorsResponseDto> handleResourceProcessingException(Exception ex) {
        log.error("An error occurred: {}", ex.getMessage(), ex);
        ErrorsResponseDto errorsResponseDto = new ErrorsResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An error occurred",
                Collections.emptyList()
        );
        return new ResponseEntity<>(errorsResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
