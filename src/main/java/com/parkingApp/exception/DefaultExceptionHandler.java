package com.parkingApp.exception;

import com.parkingApp.dto.PostNewAttendant;
import com.parkingApp.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(ParkingSlotNotFoundException.class)
    public ResponseEntity<ApiError> handleParkingSlotNotFound(
            ParkingSlotNotFoundException e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidRoleException.class, UsernameTakenException.class, UserNotFoundException.class})
    public ResponseEntity<ApiError> handleBadRequest(
            RuntimeException e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    @Override
//    public UserDTO createAttendant(PostNewAttendant postNewAttendant) {
//        try {
//
//        } catch (DataAccessException e) {
//            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
//        }
//        return null;
//    }
}