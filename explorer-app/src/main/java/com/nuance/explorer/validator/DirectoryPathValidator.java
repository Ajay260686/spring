package com.nuance.explorer.validator;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nuance.explorer.dto.PathDTO;
@Component
public class DirectoryPathValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object path, Errors errors) {
		
		String pathToValidate = ((PathDTO)path).getPath();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "path", "field.required");
		File file = new File(pathToValidate);
		if (!file.exists()) {
			errors.rejectValue("path", "path.invalid", "Path does not exist");
		}
		else {
			boolean isDirectory = file.isDirectory();
			if (!isDirectory) {
				errors.rejectValue("path", "path.invalid",
						           "The path does not point to a directory");
			}
		}
	}
}
