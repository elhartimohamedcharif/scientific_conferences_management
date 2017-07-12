package com.fsk.glc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsk.glc.entities.Comite;
import com.fsk.glc.entities.Role;
import com.fsk.glc.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public List<User> findByRoleAndComiteIsNullOrComiteIsNotIn(Role role,List<Comite> lc);
	public User findByUsername(String username);
}
