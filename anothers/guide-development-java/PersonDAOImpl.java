package com.eprogramar.springjpa.dao;

import org.springframework.stereotype.Repository;

import com.eprogramar.springjpa.model.Person;

@Repository("personDAOImpl")
public class PersonDAOImpl extends GenericDAO<Person> {	
}
