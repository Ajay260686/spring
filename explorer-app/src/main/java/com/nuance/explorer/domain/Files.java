package com.nuance.explorer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Files {

	private List<File> files;
	
	@XmlElement
	public List<File> getFiles(){
		if (files == null) {
            files = new ArrayList<>();
        }
        return files;
	}
}
