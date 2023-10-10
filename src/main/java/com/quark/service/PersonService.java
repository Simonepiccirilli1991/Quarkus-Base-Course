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
@Slf4j
public class PersonService {

    @Inject
    PersonRepo personRepo;


    public String addPerson(Person request){

        log.info("AddPerson started with request: {}",request);
        try {
            var person = new Person();
            person.setName(request.getName());
            person.setSurname(request.getSurname());
            person.setNumber(request.getNumber());
            person.setCreationDate(LocalDateTime.now());

            personRepo.persist(person);
            log.info("AddPerson ended successfully");
            return "Added correctly";
        }catch (Exception e){
            log.error("Error on addPerson with err: {}",e);
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> getPerson(String surname){
        log.info("GetPerson starts with request: {}",surname);
       try{
            var entity = personRepo.findBySurname(surname);
           log.info("GetPerson ended successfully");
            return entity;
       }catch (Exception e){
           log.error("Error on  getPerson with err: {}",e);
           throw new RuntimeException(e);
       }
    }

    public String updatePerson(UpdatePersonRequest request){

        log.info("UpdatePerson started with request: {}",request);

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
            log.error("Error on updatePerson with err: {}",e);
            throw new RuntimeException(e);
        }

        log.info("UpdatePerson ended successfully");
        return "Person updated successfully";
    }

    public String deletePerson(String surname){

        log.info("DeletePerson started with request: {}",surname);
        try{
            var person = personRepo.findBySurname(surname);

            if(person.isEmpty())
                return "Person not found";

            personRepo.delete(person.get());

            log.info("DeletePerson ended successfully");
            return "Person deleted successfully";
        }catch (Exception e){
            log.error("Error on updatePerson with err: {}",e);
            throw new RuntimeException(e);
        }
    }
}
