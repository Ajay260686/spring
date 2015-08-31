package com.nuance.explorer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuance.explorer.service.FileSystemServiceImpl;

@XmlRootElement
public class ValidationErrorDTO {

	@XmlElement
	private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

	public ValidationErrorDTO() {

	}

	public void addFieldError(String path, String message) {
		FieldErrorDTO error = new FieldErrorDTO(path, message);
		fieldErrors.add(error);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (FieldErrorDTO fieldError : fieldErrors) {
			sb.append("Field :").append(fieldError.getField())
					.append("   Field message: ")
					.append(fieldError.getMessage());
			sb.append("\n");
		}
		return sb.toString();
	}
}
