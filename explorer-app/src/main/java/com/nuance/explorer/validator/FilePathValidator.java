package com.nuance.explorer.validator;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nuance.explorer.dto.PathDTO;
@Component
public class FilePathValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object path, Errors errors) {
		
		String pathToValidate = ((PathDTO)path).getPath();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "path", "field.required");
		File file = new File(pathToValidate);
		if (!file.exists()) {
			errors.rejectValue("path", "path.invalid", "File with path does not exist");
		}
		else if(file.isDirectory()) {
			errors.rejectValue("path", "path.invalid", "Invalid path, should be a file path");
		}
	}

}
