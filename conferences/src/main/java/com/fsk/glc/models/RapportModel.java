package com.fsk.glc.models;

import org.springframework.web.multipart.MultipartFile;

import com.fsk.glc.entities.RapportType;

public class RapportModel {
	
	
	private Integer papier;
	private RapportType type;
	private  MultipartFile file;
	public Integer getPapier() {
		return papier;
	}
	public void setPapier(Integer papier) {
		this.papier = papier;
	}
	public RapportType getType() {
		return type;
	}
	public void setType(RapportType type) {
		this.type = type;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	

}
