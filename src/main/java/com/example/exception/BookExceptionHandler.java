package com.example.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class BookExceptionHandler {

	 @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
         List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
         List<String> error_message = errorList.stream()
                 .map(objErr -> objErr.getDefaultMessage())
                 .collect(Collectors.toList());
         ResponseDto responseDTO = new ResponseDto("Exception while processing REST request", error_message.toString());
         return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(BookException.class)
     public ResponseEntity<ResponseDto> handleAddressBookException(BookException exception){
         ResponseDto resDTO = new ResponseDto("Exception while processing  Book REST request", exception.getMessage());
         return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(HttpMessageNotReadableException.class)
     public ResponseEntity<ResponseDto> handleHttpMessageNotReadableException(
             HttpMessageNotReadableException exception) {
         log.error("Invalid DOB Format", exception);
         ResponseDto resDTO = new ResponseDto("Exception while processing REST Request",
                 "Date Should be in the Format of 'yyyy mm dd'");
         return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
     }

}
