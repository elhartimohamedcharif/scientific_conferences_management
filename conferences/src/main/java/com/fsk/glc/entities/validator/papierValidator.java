package com.fsk.glc.entities.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fsk.glc.models.PapierModel;


@Component
public class papierValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PapierModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PapierModel p = (PapierModel) obj;
		
		if(p.getTitre().length() < 2){
			errors.rejectValue("titre", "", "titre ne doit pas etre vide ");
		}
		
		 if (p.getFile() != null && p.getFile().isEmpty()){
	            errors.rejectValue("file", "","le fichier ne doit etre vide");
	        }
		
		if(p.getConference() == null){
			errors.rejectValue("conference", "", "!!!!");
		}
	}

}
