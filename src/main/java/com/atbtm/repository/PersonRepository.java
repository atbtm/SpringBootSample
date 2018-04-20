package com.atbtm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atbtm.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	public List<Person> findByName(String name);
	public Person findById(Integer id);
}
