package com.nuance.explorer.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class File {

	private String fullPath;
	
	private double size;
	
	private boolean isDirectory;

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
}
