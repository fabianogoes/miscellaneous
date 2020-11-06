package com.example.apidemo.service;

import com.example.apidemo.exception.NotFoundEntityException;
import com.example.apidemo.model.Person;
import com.example.apidemo.repository.PersonRepository;
import com.example.apidemo.util.UpdatePartialUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired @Qualifier("localValidatorFactoryBean")
    private Validator validator;


    public Person create(Person person) {
      log.info("create({})", person);
      repository.save(person);
      return person;
    }

    public Person read(final Long id) {
        log.info("read({})", id);
        Optional<Person> optionalPerson = repository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new NotFoundEntityException(String.format("Not found Person by id: %s", id));
        }
        return optionalPerson.get();
    }

    public List<Person> readAll() {
        log.info("readAll({})");
        return repository.findAll();
    }

    public Person update(final Long id, Person person) {
        log.info("update({}, {})", id, person);
        Optional<Person> optionalPerson = repository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new NotFoundEntityException(String.format("Not found Person by id: %s", id));
        }

        Person personUpdate = optionalPerson.get();
        personUpdate.setName(person.getName());
        personUpdate.setAddress(person.getAddress());
        personUpdate.setPhone(person.getPhone());

        return repository.save(personUpdate);
    }

    public Person updatePartial(final Long id, Map<String, Object> updates) {
        log.info("updatePartial({}, {})", id, updates);
        Optional<Person> optionalPerson = repository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new NotFoundEntityException(String.format("Not found Person by id: %s", id));
        }

        Person personUpdatePartial = optionalPerson.get();
        UpdatePartialUtil.update(Person.class, personUpdatePartial, updates);
        validate(personUpdatePartial);

        return repository.save(personUpdatePartial);
    }


    public void delete(final Long id) {
        log.info("delete({})", id);
        Optional<Person> optionalPerson = repository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new NotFoundEntityException(String.format("Not found Person by id: %s", id));
        }
        repository.delete(optionalPerson.get());
    }

    private void validate(Person person) {
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Person> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
    }


}
