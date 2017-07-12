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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author med
 *
 */
@Entity
public class Papier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id_pap")
	private Integer id;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "papier")
	private byte[] papier;
	
	@NotEmpty
	private String titre;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_conf", referencedColumnName = "id_conf", updatable = false)
	private Conference conference;

	@Column(name = "nameprogfile")
	private String nameprogfile;

	@Column(name = "typeprogfile")
	private String typeprogfile;
	
	private PapierType etat;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user_id", referencedColumnName = "user_id",updatable=false)
	 public User auteur;
	


	@ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "id_com", referencedColumnName = "id_com")
	private Comite comite;
	
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="papier")
    private Rapport rapport;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime_created")
    private Date dateCreated;
	
	
	

	public Comite getComite() {
		return comite;
	}

	public void setComite(Comite comite) {
		this.comite = comite;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Rapport getRapport() {
		return rapport;
	}

	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}


	public PapierType getEtat() {
		return etat;
	}

	public void setEtat(PapierType etat) {
		this.etat = etat;
	}
	public User getAuteur() {
		return auteur;
	}

	public void setAuteur(User auteur) {
		this.auteur = auteur;
	}

	public Integer getId() {
		return id;
	}

	public byte[] getPapier() {
		return papier;
	}

	public void setPapier(byte[] papier) {
		this.papier = papier;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Papier(byte[] papier, Conference conference, String nameprogfile,
			String typeprogfile, PapierType etat, User auteur,
			Rapport rapport, Date dateCreated) {
		super();
		this.papier = papier;
		this.conference = conference;
		this.nameprogfile = nameprogfile;
		this.typeprogfile = typeprogfile;
		this.etat = etat;
		this.auteur = auteur;
		this.rapport = rapport;
		this.dateCreated = dateCreated;
	}
	
	public Papier(String titre) {
		super();
		this.titre = titre;
	}
	public Papier(byte[] papier, Conference conference,
			 PapierType etat,  User auteur,
			Rapport rapport, Date dateCreated) {
		super();
		this.papier = papier;
		this.conference = conference;
		this.etat = etat;
		this.auteur = auteur;
		this.rapport = rapport;
		this.dateCreated = dateCreated;
	}

	public Papier() {
		super();
	}

	@Override
	public String toString() {
		return "Papier [id=" + id + ", papier=" + papier + ", etat=" + etat
				+ ", auteur=" + auteur +  ", rapport="
				+ rapport + "]";
	}
	
	
	
	

}
