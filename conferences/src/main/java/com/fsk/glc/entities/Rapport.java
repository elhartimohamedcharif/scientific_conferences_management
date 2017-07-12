package com.fsk.glc.entities;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Rapport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id_rap")
	private Integer id;
	@Column(name = "rapport_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RapportType rapportType;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "rapport")
	private byte[] rapport;
	
	@Column(name = "nameprogfile")
	private String namerapportfile;

	@Column(name = "typeprogfile")
	private String typerapportfile;
	
	
	
	@OneToOne
	@JoinColumn(name="id_pap")
	private Papier papier;
	@ManyToOne
	@JoinColumn(name="id_com")
	private Comite comite;
	
	public Comite getComite() {
		return comite;
	}
	public void setComite(Comite comite) {
		this.comite = comite;
	}
	
	
	public Papier getPapier() {
		return papier;
	}
	public void setPapier(Papier papier) {
		this.papier = papier;
	}
	public Integer getId() {
		return id;
	}
	
	public Rapport() {
		super();
	}
	
	public RapportType getRapportType() {
		return rapportType;
	}
	public void setRapportType(RapportType rapportType) {
		this.rapportType = rapportType;
	}
	public byte[] getRapport() {
		return rapport;
	}
	public void setRapport(byte[] rapport) {
		this.rapport = rapport;
	}
	public String getNamerapportfile() {
		return namerapportfile;
	}
	public void setNamerapportfile(String namerapportfile) {
		this.namerapportfile = namerapportfile;
	}
	public String getTyperapportfile() {
		return typerapportfile;
	}
	public void setTyperapportfile(String typerapportfile) {
		this.typerapportfile = typerapportfile;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Rapport [id=" + id + ", rapportType=" + rapportType
				+ ", namerapportfile=" + namerapportfile + ", typerapportfile="
				+ typerapportfile + "]";
	}
	public Rapport(RapportType rapportType, byte[] rapport,
			String namerapportfile, String typerapportfile, Papier papier,
			Comite comite) {
		super();
		this.rapportType = rapportType;
		this.rapport = rapport;
		this.namerapportfile = namerapportfile;
		this.typerapportfile = typerapportfile;
		this.papier = papier;
		this.comite = comite;
	}
	
	
	
	
	
	
	
}
