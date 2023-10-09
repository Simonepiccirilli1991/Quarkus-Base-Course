package com.quark.service;

import com.quark.model.Person;
import com.quark.repository.PersonRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepo personRepo;


    public String addPerson(Person person){

        try {
            var entity = new Person();
            entity.setName(person.getName());
            entity.setSurname(person.getSurname());
            entity.setNumber(person.getNumber());
            entity.setCreationDate(LocalDateTime.now());

            personRepo.persist(entity);
            return "Added correctly";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> getPerson(String surname){
       try{
            var entity = personRepo.findBySurname(surname);
            return entity;
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }
}
