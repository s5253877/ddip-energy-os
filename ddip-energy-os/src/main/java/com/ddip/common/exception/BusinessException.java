package com.ddip.common.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public BusinessException(String code,String message) {
		super(message);
		this.code = code;
	}
	
	public BusinessException(String code,String message,Throwable throwable) {
		super(message,throwable);
		this.code = code;
	}
	
	public BusinessException(Exception e) {
		super(e);
	}
	
}
