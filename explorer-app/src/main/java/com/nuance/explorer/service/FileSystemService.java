package com.nuance.explorer.service;

import java.util.List;

import com.nuance.explorer.domain.File;

public interface FileSystemService {

	List<File> getAllFiles(String directoryPath);
	
	File getFile(String filePath);
	
}
