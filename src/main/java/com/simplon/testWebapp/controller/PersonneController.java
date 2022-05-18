package com.simplon.testWebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplon.testWebapp.model.Personne;
import com.simplon.testWebapp.service.PersonneService;

import lombok.Data;

@Data
@Controller
public class PersonneController {
	@Autowired
	private PersonneService personneService;
	
	@GetMapping("/")
	public String accueil(Model model) {
		Iterable<Personne> listPersonne = personneService.getPersonneAll();
		model.addAttribute("personnes", listPersonne);
		return "accueil";
	}
	
	@GetMapping("/createPersonne") 
	public String createPersonne(Model model) {
		Personne p = new Personne();
		model.addAttribute("personne", p);
		return "formNewPersonne";
	}
	
	@GetMapping("/updatepersonne/{id}")
	public String updatePersonne(@PathVariable("id") final int id, Model model) {
		Personne p = personneService.getPersonne(id);
		model.addAttribute("personne", p);
		return "formUpdatePersonne";
	}
	
	@GetMapping("/deletepersonne/{id}")
	public ModelAndView deletePersonne(@PathVariable("id") final int id) {
		personneService.deletePersonne(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/savePersonne")
	public ModelAndView saveEmployee(@ModelAttribute Personne personne) {
		personneService.savePersonne(personne);
		return new ModelAndView("redirect:/");	
	}
}
