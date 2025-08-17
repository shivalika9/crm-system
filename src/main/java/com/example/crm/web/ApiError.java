//package com.example.crm.web;
//
//import java.time.Instant;
//
//public class ApiError {
//    public Instant timestamp = Instant.now();
//    public String message;
//    public String path;
//
//    public ApiError(String message, String path) {
//        this.message = message;
//        this.path = path;
//    }
//}


package com.example.crm.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private Instant timestamp = Instant.now();
    private int status;
    private String error;
    private String message;
    private String path;
    private List<FieldViolation> fieldErrors;

    public ApiError() {}

    public ApiError(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public void addFieldError(String field, String message, Object rejectedValue) {
        if (fieldErrors == null) fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldViolation(field, message, rejectedValue));
    }

    // getters & setters
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public List<FieldViolation> getFieldErrors() { return fieldErrors; }
    public void setFieldErrors(List<FieldViolation> fieldErrors) { this.fieldErrors = fieldErrors; }

    public static class FieldViolation {
        private String field;
        private String message;
        private Object rejectedValue;

        public FieldViolation() {}
        public FieldViolation(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Object getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(Object rejectedValue) { this.rejectedValue = rejectedValue; }
    }
}
