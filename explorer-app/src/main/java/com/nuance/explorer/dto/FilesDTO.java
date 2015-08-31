package com.nuance.explorer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nuance.explorer.dto.FileDTO;

@XmlRootElement
public class FilesDTO {

	private List<FileDTO> files;
	
	@XmlElement
	public List<FileDTO> getFiles(){
		if (files == null) {
            files = new ArrayList<>();
        }
        return files;
	}
}
