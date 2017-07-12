package com.fsk.glc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Conference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id_conf")
	private Integer id;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "programme")
	private byte[] programme;
	@NotEmpty
	private String titre;
	@NotEmpty
	private String lieu;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_com", referencedColumnName = "id_com", updatable = false)
	private Comite comite;

	@Column(name = "nameprogfile")
	private String nameprogfile;

	@Column(name = "typeprogfile")
	private String typeprogfile;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	@Future
	private Date dateconf;
	
	
	
	
	
	public Date getDateconf() {
		return dateconf;
	}

	public void setDateconf(Date dateconf) {
		this.dateconf = dateconf;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameprogfile() {
		return nameprogfile;
	}

	public void setNameprogfile(String nameprogfile) {
		this.nameprogfile = nameprogfile;
	}

	public String getTypeprogfile() {
		return typeprogfile;
	}

	public void setTypeprogfile(String typeprogfile) {
		this.typeprogfile = typeprogfile;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public byte[] getProgramme() {
		return programme;
	}

	public void setProgramme(byte[] programme) {
		this.programme = programme;
	}

	public Comite getComite() {
		return comite;
	}

	public void setComite(Comite comite) {
		this.comite = comite;
	}

	public Integer getId() {
		return id;
	}
	

	public Conference() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Conference(byte[] programme, String titre, String lieu,
			Comite comite, String nameprogfile, String typeprogfile) {
		super();
		this.programme = programme;
		this.titre = titre;
		this.lieu = lieu;
		this.comite = comite;
		this.nameprogfile = nameprogfile;
		this.typeprogfile = typeprogfile;
	}
	
	public Conference( String titre, String lieu) {
		super();
		this.titre = titre;
		this.lieu = lieu;
		
	}
	public Conference(byte[] programme, String titre, String lieu,
			 String nameprogfile, String typeprogfile) {
		super();
		this.programme = programme;
		this.titre = titre;
		this.lieu = lieu;
		this.nameprogfile = nameprogfile;
		this.typeprogfile = typeprogfile;
	}

	

}
