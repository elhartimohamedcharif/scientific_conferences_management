package com.fsk.glc.entities.validator;

import org.springframework.validation.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.fsk.glc.models.PapierModel;
import com.fsk.glc.models.RapportModel;


@Component
public class RapportValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return RapportModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		RapportModel  r = (RapportModel) obj;
		
		
		
		 if (r.getFile() != null && r.getFile().isEmpty()){
	            errors.rejectValue("file", "","le fichier ne doit etre vide");
	        }
		
		if(r.getType() == null){
			errors.rejectValue("type", "", "!!!!");
		}
	}

}
