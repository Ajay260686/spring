package com.nuance.explorer.dto;

public class PathDTO {

	public PathDTO(){
		
	}
	
	public PathDTO(String path) {
		super();
		this.path = path;
	}

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
