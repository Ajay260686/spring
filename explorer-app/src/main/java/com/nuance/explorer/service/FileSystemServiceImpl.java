package com.nuance.explorer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nuance.explorer.domain.File;
@Service
public class FileSystemServiceImpl implements FileSystemService {
	private List<File> files = new ArrayList<File>();
	public List<File> getAllFiles(String directoryPath) {
		
		java.io.File folder = new java.io.File(directoryPath);
		
		return getFilesForFolder(folder);
	}
	
	private List<File> getFilesForFolder(final java.io.File folder) {
		
		for(java.io.File fileEntry : folder.listFiles()) {
			if(fileEntry.isDirectory()) {				
				File file = new File();
				file.setDirectory(true);
				file.setFullPath(fileEntry.getPath());
				file.setSize(fileEntry.length() / 1024);
				files.add(file);
				getFilesForFolder(fileEntry);
			}
			else {
				File file = new File();
				file.setDirectory(false);
				file.setFullPath(fileEntry.getPath());
				file.setSize(fileEntry.length() / 1024);
				files.add(file);
			}
		}
		return files;
		
	}
	
	public File getFile(String filePath) {
		java.io.File file= new java.io.File(filePath);
		File fileToReturn = null;
		if(!file.isDirectory()) {
			fileToReturn = new File();
			fileToReturn.setFullPath(file.getPath());
			fileToReturn.setDirectory(false);
			fileToReturn.setSize(file.length()/1024);
		}
		return fileToReturn;
	}
}
