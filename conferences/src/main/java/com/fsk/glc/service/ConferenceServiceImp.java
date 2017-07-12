package com.fsk.glc.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsk.glc.dao.ComiteRepo;
import com.fsk.glc.dao.ConferenceRepo;
import com.fsk.glc.dao.PapierRepo;
import com.fsk.glc.dao.RapportRepo;
import com.fsk.glc.dao.UserRepo;
import com.fsk.glc.entities.Comite;
import com.fsk.glc.entities.Conference;
import com.fsk.glc.entities.Papier;
import com.fsk.glc.entities.PapierType;
import com.fsk.glc.entities.Rapport;
import com.fsk.glc.entities.Role;
import com.fsk.glc.entities.User;
import com.fsk.glc.exceptions.DuplicateUserNameException;

@Service
public class ConferenceServiceImp  implements IConefrenceService{

	@Autowired
	private ConferenceRepo confrepo;
	@Autowired
	private ComiteRepo comrepo;
	@Autowired
	private PapierRepo paprepo;
	@Autowired
	private UserRepo userrepo; 
	@Autowired
	private RapportRepo raprepo;
	
	
	@Override
	public void addPapier(Papier p) {
		paprepo.save(p);		
	}

	@Override
	public void affecterPapier(Integer id_conf, Set<Papier> p) {
		  Conference conf1 = confrepo.findOne(id_conf);
		  Comite com = conf1.getComite();
		  for (Papier ppp :p) {
			ppp.setComite(com);
			paprepo.save(ppp);
		  }
	}

	@Override
	public void addConference(Conference c) {
		c.setComite(new Comite());
		confrepo.save(c);
		
	}

	@Transactional(rollbackOn = DuplicateUserNameException.class)
	@Override
	public void addUser(User u) {
		userrepo.save(u);
	}

	@Override
	public void addProfTocomite(Integer id_conf, User prof) {
		Conference conf1 = confrepo.findOne(id_conf);
		conf1.getComite().getProfs().add(prof);
		Conference conf2 = confrepo.save(conf1);	
		
	}

	@Override
	public void addRapport(Rapport rapport) {
		raprepo.save(rapport);
	}

	@Override
	public void addauteur(User user) {
		userrepo.save(user);
		
	}

	@Override
	public List<Papier> getAllPapiers() {
		return paprepo.findAll();
	}

	@Override
	public List<Conference> getAllCOnferences() {
		return confrepo.findAll();
	}

	@Override
	public void deleteConference(Integer id) {
		Comite com = this.getcomtiebyconfid(id);
		confrepo.delete(id);
		
	}

	@Override
	public Conference getCOnferenceById(Integer id) {
		return confrepo.findById(id);
	}

	@Override
	public void updateConference(Conference conference) {
		confrepo.save(conference);
		
	}

	@Override
	public List<Papier> getpaptersbyidocnf(Integer id) {
		return paprepo.findByConference_Id(id);
	}

	@Override
	public List<Papier> getpaptercomiteNull(Integer id) {
		return paprepo.findByComiteIsNullAndConference_IdEquals(id);
	}

	@Override
	public Papier getpapierById(Integer id) {
		return paprepo.findById(id);
	}

	@Override
	public List<User> getprofsnonaffectes(Role role,List<Comite> lc) {
		for (Comite comite : lc) {
			System.out.println(comite);
		}
		return userrepo.findByRoleAndComiteIsNullOrComiteIsNotIn(role,lc);
	}

	@Override
	public User getUserById(Integer id) {
		return userrepo.findOne(id);
	}

	@Override
	public void affecterusers(Integer conference, Set<User> setUsers) {
		  Conference conf1 = confrepo.findOne(conference);
		  Comite com = conf1.getComite();
		  for (User u :setUsers) {
				u.setComite(com);
				userrepo.save(u);
			  }
		  
	}

	@Override
	public User getUserByusername(String username) {
		return userrepo.findByUsername(username);
	}

	@Override
	public List<Papier> getpaptersbyidauteur(Integer id) {
		return paprepo.findByAuteur_Id(id);
	}

	@Override
	public List<Papier> getpapersofcomite(Integer id) {
		return paprepo.findByComite_Id(id);
	}

	@Override
	public Comite getcomtiebyconfid(Integer id) {
		return comrepo.findByConference_Id(id);
	}

	@Override
	public Rapport getrapportById(Integer id) {
		return raprepo.findOne(id);
	}

	@Override
	public List<Papier> getPapierFinal(Integer id) {
		return paprepo.findByEtatEqualsAndConference_IdEquals(PapierType.FINAL,id);
	}

	@Override
	public List<Conference> conferenceFuture() {
		return confrepo.findByDateconfAfter(new Date());
	}

	@Override
	public void deletepaper(Integer id) {
		paprepo.delete(id);
		
	}
	
	

}
