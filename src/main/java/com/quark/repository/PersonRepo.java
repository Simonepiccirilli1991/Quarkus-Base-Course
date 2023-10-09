package com.quark.repository;

import com.quark.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PersonRepo implements PanacheRepositoryBase<Person, Long> {

    public Optional<Person> findBySurname(String surname){
        return Optional.ofNullable(find("surname",surname).firstResult());
    };

}
