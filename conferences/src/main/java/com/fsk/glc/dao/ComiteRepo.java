package com.fsk.glc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsk.glc.entities.Comite;
import com.fsk.glc.entities.Papier;

public interface ComiteRepo  extends JpaRepository<Comite, Integer>{
	public Comite findByConference_Id(Integer id);
	
}
