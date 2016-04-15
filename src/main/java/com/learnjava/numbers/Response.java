package com.learnjava.numbers;

import org.springframework.http.HttpStatus;

public class Response {
	
	static final String SUCCESS = "success";
	static final String ERROR = "error";
	
	private String success = SUCCESS; 
	
	private Integer code = HttpStatus.OK.value();
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	private String message;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}