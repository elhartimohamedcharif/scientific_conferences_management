package com.fsk.glc.models;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fsk.glc.entities.User;

public class ComiteModel {
	
	private Integer conference;
	
	private Set<Integer> profs = new LinkedHashSet<Integer>();
	
	private Set<Integer> papiers= new LinkedHashSet<Integer>();

	
	
	public Integer getConference() {
		return conference;
	}

	public void setConference(Integer conference) {
		this.conference = conference;
	}

	public Set<Integer> getProfs() {
		return profs;
	}

	public void setProfs(Set<Integer> profs) {
		this.profs = profs;
	}

	public Set<Integer> getPapiers() {
		return papiers;
	}

	public void setPapiers(Set<Integer> papiers) {
		this.papiers = papiers;
	}

	
	
	
	

}
