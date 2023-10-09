package com.quark.service;

import com.quark.model.Person;
import com.quark.model.request.AddPersonRequest;
import com.quark.model.request.UpdatePersonRequest;
import com.quark.repository.PersonRepo;
import io.quarkus.runtime.util.StringUtil;
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


    public String addPerson(Person request){

        try {
            var person = new Person();
            person.setName(request.getName());
            person.setSurname(request.getSurname());
            person.setNumber(request.getNumber());
            person.setCreationDate(LocalDateTime.now());

            personRepo.persist(person);
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

    public String updatePerson(UpdatePersonRequest request){

        if(StringUtil.isNullOrEmpty(request.surname()) || StringUtil.isNullOrEmpty(request.name())
        && StringUtil.isNullOrEmpty(request.number()))
            return "Invalid request";

        try {
            var person = personRepo.findBySurname(request.surname());

            if (person.isEmpty())
                return "Person not found";

            if (!StringUtil.isNullOrEmpty(request.name()))
                person.get().setName(request.name());
            if (StringUtil.isNullOrEmpty(request.number()))
                person.get().setNumber(request.number());

            personRepo.persist(person.get());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return "Person updated successfully";
    }

    public String deletePerson(String surname){

        try{
            var person = personRepo.findBySurname(surname);

            if(person.isEmpty())
                return "Person not found";

            personRepo.delete(person.get());

            return "Person deleted successfully";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
