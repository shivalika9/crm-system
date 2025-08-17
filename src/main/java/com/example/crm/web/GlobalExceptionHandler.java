package com.example.crm.web;

import com.example.crm.domain.CustomerSegment;
import com.example.crm.service.NotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        log.warn("NotFound: {}", ex.getMessage());
        ApiError body = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        BindingResult br = ex.getBindingResult();
        ApiError body = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "One or more fields are invalid.",
                req.getRequestURI()
        );
        for (FieldError fe : br.getFieldErrors()) {
            body.addFieldError(fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue());
        }
        log.debug("Validation error on {} -> {}", req.getRequestURI(), br.getFieldErrors());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        ApiError body = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                "Invalid request parameter(s).",
                req.getRequestURI()
        );
        Set<ConstraintViolation<?>> cvs = ex.getConstraintViolations();
        cvs.forEach(cv -> body.addFieldError(
                cv.getPropertyPath().toString(),
                cv.getMessage(),
                cv.getInvalidValue()
        ));
        log.debug("ConstraintViolation on {} -> {}", req.getRequestURI(),
                cvs.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, InvalidFormatException.class, ConversionFailedException.class})
    public ResponseEntity<ApiError> handleBadJson(Exception ex, HttpServletRequest req) {
        String message = "Malformed or invalid request body.";
        // Improve message for enum mismatch (e.g., segment wrong value)
        if (ex instanceof HttpMessageNotReadableException hmnre && hmnre.getCause() instanceof InvalidFormatException ife) {
            Class<?> target = ife.getTargetType();
            if (target != null && target.isEnum()) {
                Object[] allowed = target.getEnumConstants();
                String allowedList = allowed == null ? "" :
                        java.util.Arrays.stream(allowed).map(Object::toString).collect(Collectors.joining(", "));
                message = "Invalid value for " + target.getSimpleName() + ". Allowed: " + allowedList;
            }
        }
        ApiError body = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message,
                req.getRequestURI()
        );
        log.debug("Bad JSON on {} -> {}", req.getRequestURI(), ex.toString());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleConstraint(DataIntegrityViolationException ex, HttpServletRequest req) {
        // Common for unique email constraint, FK violations, etc.
        String msg = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
        ApiError body = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Data Conflict",
                msg,
                req.getRequestURI()
        );
        log.warn("Data conflict on {} -> {}", req.getRequestURI(), msg);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex, HttpServletRequest req) {
        ApiError body = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Unexpected error occurred.",
                req.getRequestURI()
        );
        // Full stack to logs, terse message to client
        log.error("Unhandled error on {} -> {}", req.getRequestURI(), ex.toString(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}




//package com.example.crm.web;
//
//import com.example.crm.service.NotFoundException;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.*;
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new ApiError(ex.getMessage(), req.getRequestURI()));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleOther(Exception ex, HttpServletRequest req) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ApiError(ex.getMessage(), req.getRequestURI()));
//    }
//}
