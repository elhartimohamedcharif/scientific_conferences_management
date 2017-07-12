package com.fsk.glc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fsk.glc.entities.Comite;
import com.fsk.glc.entities.Conference;
import com.fsk.glc.entities.Papier;
import com.fsk.glc.entities.Rapport;
import com.fsk.glc.entities.Role;
import com.fsk.glc.entities.User;
import com.fsk.glc.exceptions.DuplicateUserNameException;
import com.fsk.glc.models.ComiteModel;
import com.fsk.glc.service.IConefrenceService;

@Controller
@RequestMapping("/conference")
public class ConferenceController {

	@Autowired
	private IConefrenceService service;

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		model.addAttribute("conferences", this.service.getAllCOnferences());
		return "conference/index";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteContact(@PathVariable Integer id) {
		this.service.deleteConference(id);
		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addconf", method = RequestMethod.GET)
	public String addConf(Model model) {
		model.addAttribute("conference", new Conference());
		return "conference/addConference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addconference", method = RequestMethod.POST)
	public String addContact(@Valid Conference conference,
			BindingResult bindingResult, Model model,
			@RequestParam(name = "prog") MultipartFile file)
			throws IllegalStateException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		if (bindingResult.hasErrors()) {
			System.out.println(conference.getDateconf());
			return "conference/addConference";
		}
		System.out
				.println("---------------------------------------------------------------------");
		System.out.println(conference.getDateconf());
		if (!(file.isEmpty())) {

			conference.setProgramme(file.getBytes());
			conference.setNameprogfile(file.getOriginalFilename());
			conference.setTypeprogfile(file.getContentType());
		}

		service.addConference(conference);
		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/modifierconfernce/{id}", method = RequestMethod.GET)
	public String editConference(@PathVariable Integer id, Model model) {
		model.addAttribute("conference", this.service.getCOnferenceById(id));
		return "conference/editConference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
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

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/updateconf/{id}", method = RequestMethod.GET)
	public String addprog(@PathVariable Integer id, Model model) {
		model.addAttribute("conference", service.getCOnferenceById(id));
		return "conference/updateconf";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/modifierconf", method = RequestMethod.POST)
	public String modifierconf(@Valid Conference conference,
			BindingResult bindingResult, Model model,
			@RequestParam(name = "prog") MultipartFile file)
			throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "conference/updateconf";
		}
		System.out
				.println("------------------------------------------------------");
		System.out.println(conference.getId());
		if (!(file.isEmpty())) {

			conference.setProgramme(file.getBytes());
			conference.setNameprogfile(file.getOriginalFilename());
			conference.setTypeprogfile(file.getContentType());
		}

		service.updateConference(conference);
		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/getpapers/{id}" }, method = RequestMethod.GET)
	public String getpaperforconf(@PathVariable Integer id, Model model) {
		model.addAttribute("papers", this.service.getpaptersbyidocnf(id));
		return "conference/papersforconf";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/getpapersfinals/{id}" }, method = RequestMethod.GET)
	public String getpaiersfinales(@PathVariable Integer id, Model model) {
		model.addAttribute("papers", this.service.getPapierFinal(id));
		return "conference/papersfinal";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/affecter/{id}" }, method = RequestMethod.GET)
	public String affecter(@PathVariable Integer id, Model model) {
		model.addAttribute("papers", this.service.getpaptercomiteNull(id));
		ComiteModel cm = new ComiteModel();
		cm.setConference(id);
		model.addAttribute("comitepap", cm);
		return "conference/affecter";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/affecter", method = RequestMethod.POST)
	public String affecterpost(ComiteModel com, Model model) {
		Set<Papier> setPapers = new LinkedHashSet<Papier>();
		for (Integer i : com.getPapiers()) {
			Papier pap = service.getpapierById(i);
			setPapers.add(pap);
		}
		service.affecterPapier(com.getConference(), setPapers);
		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/addproftocomite/{id}" }, method = RequestMethod.GET)
	public String addproftocomite(@PathVariable Integer id, Model model) {
		List<Comite> lc = new ArrayList<Comite>();
		lc.add(service.getcomtiebyconfid(id));

		model.addAttribute("professeurs",
				this.service.getprofsnonaffectes(Role.PROF, lc));
		ComiteModel cm = new ComiteModel();
		cm.setConference(id);
		model.addAttribute("comiteprof", cm);
		return "conference/addproftocomite";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addproftocomite", method = RequestMethod.POST)
	public String addproftocomite(ComiteModel com, Model model) {
		Set<User> setUsers = new LinkedHashSet<User>();
		for (Integer i : com.getProfs()) {
			User pap = service.getUserById(i);
			setUsers.add(pap);
		}
		service.affecterusers(com.getConference(), setUsers);
		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/downloadpapierparadmin/{id}" }, method = RequestMethod.GET)
	public String downloadpapier(@PathVariable int id,
			HttpServletResponse response) throws IOException {
		Papier p = service.getpapierById(id);
		response.setContentType(p.getTypeprogfile());
		response.setContentLength(p.getPapier().length);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + p.getNameprogfile() + "\"");

		FileCopyUtils.copy(p.getPapier(), response.getOutputStream());

		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/downloadrapport/{id}" }, method = RequestMethod.GET)
	public String downloadrapport(@PathVariable int id,
			HttpServletResponse response) throws IOException {
		Rapport r = service.getrapportById(id);
		response.setContentType(r.getTyperapportfile());
		response.setContentLength(r.getRapport().length);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + r.getNamerapportfile() + "\"");

		FileCopyUtils.copy(r.getRapport(), response.getOutputStream());

		return "redirect:/conference";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public String update(Model model) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		user.setPassword("");
		model.addAttribute("ad", user);
		return "conference/update";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("ad") User ad,
			BindingResult bindingResult) throws DuplicateUserNameException {
		if (bindingResult.hasErrors()) {
			return "conference/update";
		}
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		user.setEmail(ad.getEmail());
		user.setFirstName(ad.getFirstName());
		user.setLastName(ad.getLastName());
		user.setUsername(ad.getUsername());
		user.setPassword(ad.getPassword());
		try {
			service.addUser(user);
		} catch (Exception e) {
			throw new DuplicateUserNameException(
					"Duplicate user Name for Post Title: " + user.getUsername());

		}
		return "redirect:/conference";
	}

	@ExceptionHandler(DuplicateUserNameException.class)
	public String handleError(HttpServletRequest req, Exception ex, Model mav) {

		mav.addAttribute("exception", ex);
		return "conference/execption";
	}
	
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/deletepaper/{id}" }, method = RequestMethod.GET)
	public String deletepaper(@PathVariable Integer id,Model model) {
		service.deletepaper(id);
		return "redirect:/conference/getpapers/"+id;
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = {"/affecteraucomite/{id}/{id_conf}"}, method = RequestMethod.GET)
	public String affecteraucomite(@PathVariable Integer id,@PathVariable Integer id_conf,Model model) {
		Set<Papier> setPapers = new LinkedHashSet<Papier>();
		setPapers.add(service.getpapierById(id));
		service.affecterPapier(id_conf, setPapers);
		return "redirect:/conference/getpapers/"+id_conf;
	}

}
