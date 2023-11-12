package com.ryeisaig.tech.api;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -3616363452129653651L;
	
	private List<FieldError> errors;
	
	public BadRequestException(){
		super("Bad request");
	}
	
	public BadRequestException(List<FieldError> errors){
		super("Bad request");
		this.errors = errors;
	}
	
	public BadRequestException(String message){
		super(message);
	}
}
