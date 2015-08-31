package com.nuance.explorer.exception;

import org.springframework.validation.BindingResult;

public class InvalidPathException extends Exception {

	private static final long serialVersionUID = 1L;

	private BindingResult bindingResult;

	public InvalidPathException(BindingResult bindingResult, String message,
			Throwable cause) {
		super(message, cause);
		this.bindingResult = bindingResult;

	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

}
