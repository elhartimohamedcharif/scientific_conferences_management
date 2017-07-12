package com.fsk.glc.models;
import org.springframework.web.multipart.MultipartFile;

import com.fsk.glc.entities.PapierType;
public class PapierModel {
	
	private Integer id;
	private Integer conference;
	private PapierType etat;
	private String titre;
	private  MultipartFile file;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Integer getConference() {
		return conference;
	}
	public void setConference(Integer conference) {
		this.conference = conference;
	}
	public PapierType getEtat() {
		return etat;
	}
	public void setEtat(PapierType etat) {
		this.etat = etat;
	}
	
	
	
	
	

}
