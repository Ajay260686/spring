package com.nuance.explorer.service;

import java.util.List;

import com.nuance.explorer.dto.FileDTO;

public interface FileSystemService {

	List<FileDTO> getAllFiles(String directoryPath);
	
	FileDTO getFile(String filePath);
	
}
