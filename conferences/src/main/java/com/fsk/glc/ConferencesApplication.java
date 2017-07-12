package com.fsk.glc;



import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fsk.glc.dao.ComiteRepo;
import com.fsk.glc.dao.PapierRepo;
import com.fsk.glc.dao.UserRepo;
import com.fsk.glc.entities.Comite;
import com.fsk.glc.entities.Conference;
import com.fsk.glc.entities.Papier;
import com.fsk.glc.entities.PapierType;
import com.fsk.glc.entities.Rapport;
import com.fsk.glc.entities.RapportType;
import com.fsk.glc.entities.Role;
import com.fsk.glc.entities.User;
import com.fsk.glc.service.IConefrenceService;

@SpringBootApplication
public class ConferencesApplication {

	
	public static void main(String[] args) {
	 ApplicationContext ctx= SpringApplication.run(ConferencesApplication.class, args);
		
	 IConefrenceService ur = ctx.getBean(IConefrenceService.class);
	
	User u = ur.getUserById(1);
	if(u==null)
	ur.addUser(new User(1,"admin", "admin", "admin", "admin", "admin@gmail.com", Role.ADMIN));
	 
	Papier p =ur.getpapierById(1);
	
	if(p.getComite()==null)
	System.out.println(p.getComite());
	  
	   
	   
	}
}
