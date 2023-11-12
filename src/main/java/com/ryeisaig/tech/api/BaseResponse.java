package com.ryeisaig.tech.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {
	
	private String code;
	
	private String title;
	
	private String message;
	
	private T content;
	
	private Map<String, String> errors;
	
	public BaseResponse(String code, String title, String message, T content){
		this.code = code;
		this.title = title;
		this.content = content;
		this.message = message;
	}
		
	public static <T> BaseResponse<T> success(T content, String message) {
		return new BaseResponse<T>(ResultCodes.SUCCESS, Messages.SUCCESS, message, content);
	}
	
	public static <T> BaseResponse<T> success(T content) {
		return new BaseResponse<T>(ResultCodes.SUCCESS, Messages.SUCCESS, null, content);
	}
	
	public static <T> BaseResponse<T> success() {
		return new BaseResponse<T>(ResultCodes.SUCCESS, Messages.SUCCESS, null, null);
	}

	public static <T> BaseResponse<T> notFound() {
		return new BaseResponse<T>(ResultCodes.NOT_FOUND, Messages.NOT_FOUND, null, null);
	}

	public static <T> BaseResponse<T> badRequest(String message) {
		return new BaseResponse<T>(ResultCodes.ERROR, Messages.ERROR, message, null);
	}
	
	public static <T> BaseResponse<T> badRequestExisting(String message) {
		return new BaseResponse<T>(ResultCodes.EXISTING, Messages.ERROR, message, null);
	}
	
	public static <T> BaseResponse<T> badRequest(String message, Map<String, String> errors) {
		return new BaseResponse<T>(ResultCodes.ERROR, Messages.ERROR, message, null, errors);
	}
	
	public static <T> BaseResponse<T> error(String message) {
		return new BaseResponse<T>(ResultCodes.INTERNAL_ERROR, Messages.ERROR, message, null);
	}
	
	public static <T> BaseResponse<T> error(String message, Map<String, String> errors) {
		return new BaseResponse<T>(ResultCodes.INTERNAL_ERROR, Messages.ERROR, message, null, errors);
	}
}
