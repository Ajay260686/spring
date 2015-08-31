package com.nuance.explorer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nuance.explorer.dto.FileDTO;

import java.util.ArrayList;
import java.util.List;
@Service
public class FileSystemServiceImpl implements FileSystemService {
	
	private static final transient Logger log = LoggerFactory.getLogger(FileSystemServiceImpl.class.getName());
	
	private List<FileDTO> files = new ArrayList<FileDTO>();
	
	public List<FileDTO> getAllFiles(String directoryPath) {

		java.io.File folder = new java.io.File(directoryPath);
		List<FileDTO> files = getFilesForFolder(folder);
		log.info(
				"Retrieved all files and directories recursively for the directory path = {}",
				new Object[] { directoryPath });
		return files;
	}
	
	private List<FileDTO> getFilesForFolder(final java.io.File folder) {
		
		for(java.io.File fileEntry : folder.listFiles()) {
			if(fileEntry.isDirectory()) {				
				FileDTO file = new FileDTO();
				file.setDirectory(true);
				file.setFullPath(fileEntry.getPath());
				file.setSize(fileEntry.length());
				files.add(file);
				getFilesForFolder(fileEntry);
			}
			else {
				FileDTO file = new FileDTO();
				file.setDirectory(false);
				file.setFullPath(fileEntry.getPath());
				file.setSize(fileEntry.length());
				files.add(file);
			}
		}
		return files;
	}
	
	public FileDTO getFile(String filePath) {
		
		java.io.File file= new java.io.File(filePath);
		
		FileDTO fileToReturn = null;
		
		if(!file.isDirectory()) {
			fileToReturn = new FileDTO();
			fileToReturn.setFullPath(file.getPath());
			fileToReturn.setDirectory(file.isDirectory());
			fileToReturn.setSize(file.length());
			fileToReturn.setExecutable(file.canExecute());
			fileToReturn.setReadable(file.canRead());
			fileToReturn.setWritable(file.canWrite());
			fileToReturn.setFreeSpace(file.getFreeSpace());
			fileToReturn.setTotalSpace(file.getTotalSpace());
			fileToReturn.setUsableSpace(file.getUsableSpace());
			fileToReturn.setLastModified(file.lastModified());
		}
		log.info("Retrieved file with path = {}", new Object[] { filePath });
		return fileToReturn;
	}
}
