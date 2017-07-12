package com.fsk.glc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  boolean alwaysUseDefaultSuccess = true;
      http
          .authorizeRequests()
            .antMatchers("/auteur/**").hasAuthority("AUTEUR")
            .antMatchers("/conference/**").hasAnyAuthority("ADMIN")
            .antMatchers("/prof/**").hasAnyAuthority("PROF")
            .antMatchers("/login","/about","/register","/","/addprof","/signup","/downloadprogramme/**").permitAll()
            
            //all other requests
            .anyRequest().authenticated()
            .and()
          // login form configuration
          .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginok")
            .failureUrl("/login?error")
            .defaultSuccessUrl("/",alwaysUseDefaultSuccess)
            .permitAll()
            .and()
          //logout configuration
          .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");
      
      
      
      
      
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

}
