package com.quark.service;

import com.quark.model.Person;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    private Map<String,Person> personList = new HashMap<>();
    public String addPerson(Person person){

        personList.put(person.getSurname(),person);
        return "Added correctly";
    }

    public Person getPerson(String surname){

        var person = Optional.ofNullable(personList.get(surname));

        if(person.isEmpty())
            return new Person();
        else
            return person.get();
    }
}
