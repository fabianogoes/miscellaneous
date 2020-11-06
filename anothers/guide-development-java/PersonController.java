package com.eprogramar.springjpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eprogramar.springjpa.model.Person;
import com.eprogramar.springjpa.service.PersonService;

@Controller
public class PersonController {

private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	private PersonService personService;

	@Autowired
	@Qualifier("personService")
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public PersonService getPersonDAOImpl() {
		return this.personService;
	}	
	
	@RequestMapping(value = "/persistPerson", method = RequestMethod.GET)
	public ModelAndView persistPerson(Person person){
		logger.info("persistPerson("+person+")");
		this.personService.persist(person);		
		return viewPerson();
	}
	
	@RequestMapping(value = "/viewPerson", method = RequestMethod.GET)
	public ModelAndView viewPerson(){
		logger.info("viewPerson()...");
		ModelAndView mv = new ModelAndView("person");
		mv.addObject("list", this.personService.getAll());
		return mv;
	}
	
}
