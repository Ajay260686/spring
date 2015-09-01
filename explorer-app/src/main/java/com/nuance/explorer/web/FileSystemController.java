package com.nuance.explorer.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nuance.explorer.dto.FileDTO;
import com.nuance.explorer.dto.FilesDTO;
import com.nuance.explorer.dto.PathDTO;
import com.nuance.explorer.dto.ValidationErrorDTO;
import com.nuance.explorer.exception.InvalidPathException;
import com.nuance.explorer.service.FileSystemService;
import com.nuance.explorer.service.FileSystemServiceImpl;
import com.nuance.explorer.validator.DirectoryPathValidator;
import com.nuance.explorer.validator.FilePathValidator;

@RestController
@RequestMapping("/fileexplorer")
public class FileSystemController {

	private static final transient Logger log = LoggerFactory.getLogger(FileSystemServiceImpl.class.getName());
	
	@Autowired
	private FileSystemService fileSystemService;
	
	@Autowired
	private DirectoryPathValidator directoryPathValidator;
	
	@Autowired
	private FilePathValidator filePathValidator;
	
	@Autowired
	private MessageSource messageSource;
	
    @RequestMapping(value="/filesAndDirectories",
    		        method = RequestMethod.GET, 
    		        headers = "Accept=application/json")
    @ResponseBody
    public Object getAllFilesAndDirectories(@ModelAttribute PathDTO pathDto,
    		                               BindingResult result) throws InvalidPathException {
    	
    	//Errors
    	directoryPathValidator.validate(pathDto, result);
		if (result.hasErrors()) {
			log.debug("Could not getAllFilesAndDirectories, path is invalid");
			throw new InvalidPathException(result, "Invalid path", null);
		}
		else {
			log.debug("Request parameter is OK...fetching files for directory path....");
			FilesDTO files = new FilesDTO();
			files.getFiles().addAll(fileSystemService.getAllFiles(pathDto.getPath()));
			return files;
		}
    }
    
	@RequestMapping(value = "/file", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public FileDTO getFileDetails(@ModelAttribute PathDTO pathDto,
								  BindingResult result) throws InvalidPathException {

		filePathValidator.validate(pathDto, result);
		if (result.hasErrors()) {
			log.debug("Received an exception while processing the request");
			throw new InvalidPathException(result,
					"An error occurred while processing the request", null);
		} else {
			return fileSystemService.getFile(pathDto.getPath());
		}
	}

    @ExceptionHandler(InvalidPathException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(InvalidPathException exception) {
        
    	BindingResult result = exception.getBindingResult();
    	List<FieldError> fieldErrors = result.getFieldErrors();
    	return processFieldErrors(fieldErrors);
    }
 
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        
    	ValidationErrorDTO dto = new ValidationErrorDTO();
        
        for (FieldError fieldError: fieldErrors) {
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        return dto;
    }

}
