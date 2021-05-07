package com.cts.training.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@Controller
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));//(timestamp,message,details)
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));//(timestamp,message,details)
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status,WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validation Failed",ex.getBindingResult().toString());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
