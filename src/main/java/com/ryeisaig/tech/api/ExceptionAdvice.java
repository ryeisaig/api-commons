package com.ryeisaig.tech.api;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<?> badRequest(BadRequestException e) {
		log.warn(e.getMessage(), e);		
		return new ResponseEntity<>(BaseResponse.badRequestExisting(e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> inputNotValid(MethodArgumentNotValidException e) {
		log.warn(e.getMessage());
		
		Map<String, String> mappedErrors = null;
		
		if(e.getFieldErrors() != null) {
			mappedErrors = e.getFieldErrors().stream().collect(Collectors.toMap(p -> p.getField(), p -> p.getDefaultMessage()));
		}
		
		return new ResponseEntity<BaseResponse<?>>(BaseResponse.badRequest(Messages.CHECK_INPUT, mappedErrors),
				HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<?> defaultRuntimeException(RuntimeException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<BaseResponse<?>>(BaseResponse.error(e.getLocalizedMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> defaultException(Exception e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<BaseResponse<?>>(BaseResponse.error(e.getLocalizedMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
