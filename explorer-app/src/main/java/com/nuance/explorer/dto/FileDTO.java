package com.nuance.explorer.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileDTO {

	private String fullPath;
	
	private long size;
	
	private boolean isDirectory;
	
	private boolean canExecute;
	
	private boolean canRead;
	
	private boolean canWrite;
	
	private long freeSpace;
	
	private long totalSpace;
	
	private long usableSpace;
	
	private boolean isHidden;
	
	private long lastModified;
	
	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public double getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public boolean canExecute() {
		return this.canExecute;
	}

	public void setExecutable(boolean canExecute) {
		this.canExecute = canExecute;
	}

	public boolean isCanRead() {
		return canRead;
	}

	public void setReadable(boolean canRead) {
		this.canRead = canRead;
	}

	public boolean canWrite() {
		return canWrite;
	}

	public void setWritable(boolean canWrite) {
		this.canWrite = canWrite;
	}

	public long getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(long freeSpace) {
		this.freeSpace = freeSpace;
	}

	public long getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(long totalSpace) {
		this.totalSpace = totalSpace;
	}

	public long getUsableSpace() {
		return usableSpace;
	}

	public void setUsableSpace(long usableSpace) {
		this.usableSpace = usableSpace;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
}
