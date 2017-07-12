package com.fsk.glc.entities;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fsk.glc.entities.validator.ExtendedEmailValidator;


@Entity
@Table(name = "USER")
public class User  implements UserDetails{

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "FIRST_NAME")
	@NotEmpty
	private String firstName;

	@Column(name = "LAST_NAME")
	@NotEmpty
	private String lastName;

	@Column(name = "USERNAME",unique = true)
	@NotEmpty
	private String username;

	@Column(name = "PASSWORD")
	@NotEmpty
	private String password;

	@Basic
	@ExtendedEmailValidator(message="email invalide")
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ROLE")
	private Role role;

	@ManyToOne
   	@JoinColumn(name = "id_com", referencedColumnName = "id_com")
	private Comite comite;

	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Comite getComite() {
		return comite;
	}

	public void setComite(Comite comite) {
		this.comite = comite;
	}


	public User(String firstName, String lastName, String username,
			String password, String email, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}


	public User(String firstName, String lastName, String username,
			String password, String email, Role role, Comite comite) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.comite = comite;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", username=" + username + ", password="
				+ password + ", email=" + email + ", role=" + role
				+ ", comite=" + comite + "]";
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(String.valueOf(this.role));
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public User(Integer id, String firstName, String lastName, String username,
			String password, String email, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	

	
}