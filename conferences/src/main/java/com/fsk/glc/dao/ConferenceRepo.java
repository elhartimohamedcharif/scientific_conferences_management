package com.fsk.glc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsk.glc.entities.Conference;

public interface ConferenceRepo extends JpaRepository<Conference, Integer>{

	public Conference findById(Integer id);
	public List<Conference> findByDateconfAfter(Date d);

}
