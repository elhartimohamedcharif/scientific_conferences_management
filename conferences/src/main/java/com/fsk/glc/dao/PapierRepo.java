package com.fsk.glc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fsk.glc.entities.Papier;
import com.fsk.glc.entities.PapierType;

public interface PapierRepo extends JpaRepository<Papier, Integer>{
	
	public List<Papier> findByConference_Id(Integer id);
	public List<Papier> findByComite_Id(Integer id);
	public List<Papier> findByComite_IdIn(List<Integer> l);
	public List<Papier> findByAuteur_Id(Integer id);
	public Papier findById(Integer id);
	public List<Papier> findByComiteIsNullAndConference_IdEquals(Integer id);
	public List<Papier> findByEtatEqualsAndConference_IdEquals(PapierType p, Integer id);
	

}
