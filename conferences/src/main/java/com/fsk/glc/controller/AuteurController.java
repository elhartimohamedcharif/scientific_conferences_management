package com.fsk.glc.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsk.glc.entities.Papier;
import com.fsk.glc.entities.PapierType;
import com.fsk.glc.entities.Rapport;
import com.fsk.glc.entities.RapportType;
import com.fsk.glc.entities.User;
import com.fsk.glc.entities.validator.papierValidator;
import com.fsk.glc.models.PapierModel;
import com.fsk.glc.service.IConefrenceService;


@Controller
@RequestMapping("/auteur")
public class AuteurController {
	
	@Autowired
	private papierValidator paperModelValidator;
	
	 
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(paperModelValidator);
	}


	@Autowired
	private IConefrenceService service;
	
	 @RequestMapping(value="/addpaper",method=RequestMethod.GET)
		public String addPapier(Model model){
		 User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	        System.out.println(user);
			model.addAttribute("papierModel", new PapierModel());
			model.addAttribute("conferences",service.conferenceFuture());
			return "conference/addpaper";
		}
	 
	 @RequestMapping(value = "/addpaper", method = RequestMethod.POST)
	    public String addpaper(@Valid PapierModel papierModel,BindingResult bindingResult,Model model) throws IllegalStateException, IOException {
		 if (bindingResult.hasErrors()) {
			 	model.addAttribute("conferences",service.conferenceFuture());
			 
			 return "conference/addpaper";
	        }
	        Papier p = new Papier();
	        p.setTitre(papierModel.getTitre());
	        p.setEtat(PapierType.NON);
	        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        p.setAuteur(user);
	        p.setConference(service.getCOnferenceById(papierModel.getConference()));
	        p.setDateCreated(new Date());
	        if(!(papierModel.getFile().isEmpty())){ 
	        	
	        	
	        	p.setPapier(papierModel.getFile().getBytes());
	        	p.setNameprogfile(papierModel.getFile().getOriginalFilename());
	        	p.setTypeprogfile(papierModel.getFile().getContentType());
	        }
	        	
	    		service.addPapier(p);
	    		return "redirect:/auteur/getmypapers";
	    }
	 
	 @RequestMapping(value = {"/getmypapers" },method=RequestMethod.GET)
	    public String getpaperforconf(Model model) {
	        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        model.addAttribute("papers", this.service.getpaptersbyidauteur(user.getId()));
	        return "conference/papersofautor";
	    }
	 @RequestMapping(value = { "/downloadpapierparauteur/{id}" }, method = RequestMethod.GET)
	    public String downloadpapier(@PathVariable int id, HttpServletResponse response) throws IOException {
	        Papier p = service.getpapierById(id);
	        response.setContentType(p.getTypeprogfile());
	        response.setContentLength(p.getPapier().length);
	        response.setHeader("Content-Disposition","attachment; filename=\"" + p.getNameprogfile() +"\"");
	  
	        FileCopyUtils.copy(p.getPapier(), response.getOutputStream());
	  
	        return "redirect:/getmypapers";
	    }
	 
	 @RequestMapping(value = { "/downloadrapport/{id}" }, method = RequestMethod.GET)
	    public String downloadrapport(@PathVariable int id, HttpServletResponse response) throws IOException {
	        Rapport r = service.getrapportById(id);
	        response.setContentType(r.getTyperapportfile());
	        response.setContentLength(r.getRapport().length);
	        response.setHeader("Content-Disposition","attachment; filename=\"" + r.getNamerapportfile() +"\"");
	  
	        FileCopyUtils.copy(r.getRapport(), response.getOutputStream());
	  
	        return "redirect:/getmypapers";
	    }
	 
	 
	 
	 @RequestMapping(value="/addfinal/{conference}/{id}",method=RequestMethod.GET)
		public String addfinal(@PathVariable int conference,@PathVariable int id,Model model){
		 if(service.getpapierById(id) != null)
		 	if(service.getpapierById(id).getRapport().getRapportType()==RapportType.ACCEPTE  && service.getpapierById(id).getEtat() == PapierType.NON){
			 	PapierModel pm= new PapierModel();
			 	pm.setConference(conference);
			 	pm.setId(id);
			 	pm.setTitre("rien");
				model.addAttribute("papierModel", pm);
				return "conference/addfinal";
		 	}else{
		 		 return "redirect:/auteur/getmypapers";
		 	}
		 return "redirect:/auteur/getmypapers";
		 
		}
	 
	 @RequestMapping(value = "/addfinal", method = RequestMethod.POST)
	    public String addfinal(@Valid PapierModel papierModel,BindingResult bindingResult,Model model) throws IllegalStateException, IOException {
		 if (bindingResult.hasErrors()) {
			  return "conference/addfinal";
	        }
	        Papier p = service.getpapierById(papierModel.getId());
	        p.setTitre(p.getTitre()+"_version finale");
	        p.setEtat(PapierType.FINAL);
	        if(!(papierModel.getFile().isEmpty())){ 
	        	
	        	
	        	p.setPapier(papierModel.getFile().getBytes());
	        	p.setNameprogfile(papierModel.getFile().getOriginalFilename());
	        	p.setTypeprogfile(papierModel.getFile().getContentType());
	        }
	        	
	    		service.addPapier(p);
	    		return "redirect:/auteur/getmypapers";
	    }
}

