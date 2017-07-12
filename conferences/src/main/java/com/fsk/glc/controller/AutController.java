package com.fsk.glc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsk.glc.entities.Conference;
import com.fsk.glc.entities.Role;
import com.fsk.glc.entities.User;
import com.fsk.glc.exceptions.DuplicateUserNameException;
import com.fsk.glc.service.IConefrenceService;

@Controller
public class AutController {
	@Autowired
	private IConefrenceService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "conference/login";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		return "conference/about";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "conference/regiter";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String ajouteruser(@Valid User user, BindingResult bindingResult,
			Model model) throws DuplicateUserNameException {
		if (bindingResult.hasErrors()) {
			return "conference/regiter";
		}
		user.setRole(Role.AUTEUR);
		try {
			service.addUser(user);
		} catch (Exception e) {
			throw new DuplicateUserNameException(
					"Duplicate user Name for Post Title: " + user.getUsername());

		}
		return "redirect:/conference";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("conferences", this.service.conferenceFuture());
		return "conference/public";
	}

	@RequestMapping(value = "/addprof", method = RequestMethod.GET)
	public String addprof(Model model) {
		model.addAttribute("user", new User());
		return "conference/addprof";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String ajouterprof(@Valid User user, BindingResult bindingResult,
			Model model) throws DuplicateUserNameException {
		if (bindingResult.hasErrors()) {
			return "conference/addprof";
		}
		user.setRole(Role.PROF);
		try {
			service.addUser(user);
		} catch (Exception e) {
			throw new DuplicateUserNameException(
					"Duplicate user Name for Post Title: " + user.getUsername());

		}
		return "conference/login";
	}

	@RequestMapping(value = { "/downloadprogramme/{id}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int id,
			HttpServletResponse response) throws IOException {
		Conference conf = service.getCOnferenceById(id);
		response.setContentType(conf.getTypeprogfile());
		response.setContentLength(conf.getProgramme().length);
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ conf.getNameprogfile() + "\"");

		FileCopyUtils.copy(conf.getProgramme(), response.getOutputStream());

		return "redirect:/conference";
	}
	
	 @ExceptionHandler(DuplicateUserNameException.class)
	 public String handleError(HttpServletRequest req, Exception ex, Model mav) {
		 	
		    mav.addAttribute("exception", ex);
		    return "conference/execption";
		  }

}
