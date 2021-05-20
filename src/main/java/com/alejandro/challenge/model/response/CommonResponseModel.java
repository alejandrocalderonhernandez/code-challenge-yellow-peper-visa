package com.alejandro.challenge.model.response;

import java.util.List;

public class CommonResponseModel {

	private String status;
	private  List<String> errors;
	
	public CommonResponseModel(String status, List<String> errors) {
		super();
		this.status = status;
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
