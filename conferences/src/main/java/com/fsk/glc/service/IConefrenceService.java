package com.fsk.glc.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fsk.glc.entities.Comite;
import com.fsk.glc.entities.Conference;
import com.fsk.glc.entities.Papier;
import com.fsk.glc.entities.Rapport;
import com.fsk.glc.entities.Role;
import com.fsk.glc.entities.User;

public interface IConefrenceService {
	
	
	public void addPapier(Papier p);
	public void affecterPapier(Integer id_conf,Set<Papier> p);
	public void addConference(Conference c);
	public void deleteConference(Integer id);
	public void addUser(User prof);
	public void addProfTocomite(Integer id_conf,User prof);
	public void addRapport(Rapport rapport);
	public void addauteur(User user);
	public List<Papier>  getAllPapiers();
	public List<Conference> getAllCOnferences();
	public Conference getCOnferenceById(Integer id);
	public void updateConference(Conference conference);
	public List<Papier> getpaptersbyidocnf(Integer id);
	public List<Papier> getpaptersbyidauteur(Integer id);
	public List<Papier> getpaptercomiteNull(Integer id);
	public Papier getpapierById(Integer id);
	public List<User> getprofsnonaffectes(Role role,List<Comite> lc);
	public User getUserById(Integer id);
	public void affecterusers(Integer conference, Set<User> setUsers);
	public User getUserByusername(String username);
	public List<Papier> getpapersofcomite(Integer id);
	public Comite getcomtiebyconfid(Integer id);
	public Rapport getrapportById(Integer id);
	public List<Papier> getPapierFinal(Integer id);
	public List<Conference> conferenceFuture();
	public void deletepaper(Integer id);

	


}
