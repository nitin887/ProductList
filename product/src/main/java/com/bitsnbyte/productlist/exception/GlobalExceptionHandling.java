package com.bitsnbyte.productlist.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bitsnbyte.productlist.dto.ExceptionResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandling {
        @ExceptionHandler(CategoryAlreadyExistsException.class)
        public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExistsException(
                        CategoryAlreadyExistsException e,
                        WebRequest webrequest) {
                ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(webrequest.getDescription(false),
                                e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);

        }

        @ExceptionHandler(CategoryNotFoundException.class)
        public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException e,
                        WebRequest webrequest) {

                ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(webrequest.getDescription(false),
                                e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);

        }

        // @ExceptionHandler(Exception.class)
        // public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception
        // e,
        // WebRequest webrequest) {
        // ExceptionResponseDTO exceptionResponseDTO = new
        // ExceptionResponseDTO(webrequest.getDescription(false),
        // e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
        //
        // }

}
