package com.simplon.testWebapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.simplon.testWebapp.model.Personne;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonneRepository {
	@Autowired
	private CustomProperties customProperties;
	
	/**
	 * Obtenir tous les employés
	 * @return Un itérable de tous les employés
	 */
	public Iterable<Personne> getPersones(){
		String baseApiUrl = customProperties.getApiUrl();
		String getPersonnesUrl = baseApiUrl + "/personnes";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Personne>> response = restTemplate.exchange(
				getPersonnesUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Personne>>() {}
				);
		log.debug("Get Personnes call " + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/**
	 * Obtenir un employé par l'id
	 * @param id L'identifiant de l'employé
	 * @return L'employé qui correspond à l'identifiant
	 */
	 
	public Personne getPersonne(int id) {
		String baseApiUrl = customProperties.getApiUrl();
		String getPersonnesUrl = baseApiUrl + "/personne/" +  id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Personne> response = restTemplate.exchange(
				getPersonnesUrl,
				HttpMethod.GET,
				null,
				Personne.class
				);
		log.debug("Get Personnes call " + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/**
	 * Supprimer un employé en utilisant la méthode d'échange de RestTemplate
	 * au lieu de supprimer la méthode afin de consigner le code d'état de la réponse.
	 * @param e L'employé à supprimer
	 */
	
	public void delete(int id) {
		String baseApiUrl = customProperties.getApiUrl();
		String getPersonnesUrl = baseApiUrl + "/personne/" +  id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				getPersonnesUrl,
				HttpMethod.DELETE,
				null,
				Void.class
				);
		log.debug("Delete Personnes call " + response.getStatusCode().toString());
	}
	
	/**
	* Ajouter un nouvel employé
	* @param e Un nouvel employé (sans identifiant)
	* @return L'employé rempli au complet (avec un identifiant)
	*/
	
	public Personne createPersonne(Personne personne) {
		String baseApiUrl = customProperties.getApiUrl();
		String getPersonnesUrl = baseApiUrl + "/personne";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Personne> request = new HttpEntity<Personne>(personne);
		ResponseEntity<Personne> response = restTemplate.exchange(
				getPersonnesUrl,
				HttpMethod.POST,
				request,
				Personne.class 
				);
		
		log.debug("create Personnes call " + response.getStatusCode().toString());
		return response.getBody();
	}
	
	/**
	* Mettre à jour un employé - en utilisant la méthode HTTP PUT.
	* @param e Employé existant à mettre à jour
	*/
	
	public Personne updatePersonne(Personne personne) {
		String baseApiUrl = customProperties.getApiUrl();
		String getPersonnesUrl = baseApiUrl + "/personne/" + personne.getId();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Personne> request = new HttpEntity<Personne>(personne);
		ResponseEntity<Personne> response = restTemplate.exchange(
				getPersonnesUrl,
				HttpMethod.PUT,
				request,
				Personne.class
				);
		
		log.debug("Update Personnes call " + response.getStatusCode().toString());
		return response.getBody();
	}
}
