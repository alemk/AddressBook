package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressBookRepository extends CrudRepository<Addressbook, Long>{

    List<Addressbook> findByLastname(String lastname);
}
