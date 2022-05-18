package com.simplon.testWebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.testWebapp.model.Personne;
import com.simplon.testWebapp.repository.PersonneRepository;
import com.simplon.testWebapp.service.PersonneService;

import lombok.Data;

@Data
@Service
public class PersonneService {
	@Autowired
	private PersonneRepository personneRepository;
	
	public Personne getPersonne(int id){
		return personneRepository.getPersonne(id);
	}
	
	public Iterable<Personne> getPersonneAll(){
		return personneRepository.getPersones();
	}
	
	public void deletePersonne(int id) {
		 personneRepository.delete(id);
	}
	
	public Personne savePersonne(Personne personne) {
		Personne p;
		
		if (personne.getId() == 0) {
			p = personneRepository.createPersonne(personne);
		} else {
			p = personneRepository.updatePersonne(personne);
		}
		
		
		return p;
	}
}
