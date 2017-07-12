package com.fsk.glc.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
@Entity
public class Comite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_com")
	private Integer id;
		
	@OneToMany(fetch=FetchType.EAGER,mappedBy="comite")
	//@JoinColumn(name="id_com")
	private Set<User> profs =  new LinkedHashSet<User>();
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="comite")
	//@JoinColumn(name="id_com")
	private Set<Papier> papier =  new LinkedHashSet<Papier>();
	
	
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Conference conference;
	

	public Set<Papier> getPapier() {
		return papier;
	}
	public void setPapier(Set<Papier> papier) {
		this.papier = papier;
	}
	public Conference getConference() {
		return conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	public Set<User> getProfs() {
		return profs;
	}
	public void setProfs(Set<User> profs) {
		this.profs = profs;
	}

	public Integer getId() {
		return id;
	}
	public Comite(Set<User> profs, Conference conference) {
		super();
		this.profs = profs;
		this.conference = conference;
	}
	
	public Comite(Set<User> profs) {
		super();
		this.profs = profs;
	}
	public Comite() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comite [id=" + id +"]";
	}
	public Comite(Conference conference) {
		super();
		this.conference = conference;
	}
	
	
	
	
	
	

}
