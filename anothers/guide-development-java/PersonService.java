package com.eprogramar.springjpa.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eprogramar.springjpa.dao.PersonDAOImpl;
import com.eprogramar.springjpa.model.Person;

@Service("personService")
public class PersonService {
	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private PersonDAOImpl personDAOImpl;
	
	public void setPersonDAOImpl(PersonDAOImpl personDAOImpl){
		this.personDAOImpl = personDAOImpl;
	}
	
	public void persist(Person entity) {
		logger.info("persist("+entity+")...");
		this.personDAOImpl.persist(entity);
	}
	
	public List<Person> getAll(){
		logger.info("getAll()...");
		return this.personDAOImpl.getAll(Person.class);
	}
	
	public Person findById(Long id){
		logger.info("findById("+id+")...");
		return this.personDAOImpl.findById(Person.class, id);
	}	

}
