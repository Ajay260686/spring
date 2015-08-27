package com.nuance.explorer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nuance.explorer.domain.File;
import com.nuance.explorer.domain.Files;
import com.nuance.explorer.service.FileSystemService;

@RestController
@RequestMapping("/fileexplorer")
public class FileSystemController {

	private final FileSystemService fileSystemService;
	
    @Autowired
    public FileSystemController(FileSystemService clinicService) {
        this.fileSystemService = clinicService;
    }
    
    @RequestMapping(value="/filesAndDirectories",
    		        method = RequestMethod.GET, 
    		        headers = "Accept=application/json")
    @ResponseBody
    public Files getAllFilesAndDirectories(@RequestParam("directoryPath") String directoryPath) {
    	
    	Files files = new Files();
    	files.getFiles().addAll(fileSystemService.getAllFiles(directoryPath));
    	return files;
    }
    
    @RequestMapping(value="/file",
    				method = RequestMethod.GET,
    				headers = "Accept=application/json")
    @ResponseBody
    public File getFileDetails(@RequestParam("filePath") String filePath){
    	return fileSystemService.getFile(filePath);
    }

}
