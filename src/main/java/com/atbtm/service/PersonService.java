package com.atbtm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atbtm.domain.Person;
import com.atbtm.enums.HttpResultEnum;
import com.atbtm.exception.PersonAgeException;
import com.atbtm.repository.PersonRepository;
import org.springframework.data.domain.Sort;

//making the db access atomic. All or nothing. Writing normally should all have this
@Transactional
@Service
public class PersonService {
	@Autowired
	PersonRepository pr;
	public void addTwo() {
		Person p1 = new Person();
		p1.setAge(12);
		p1.setName("Person1");
		pr.save(p1);

		Person p2 = new Person();
		p2.setAge(13);
		p2.setName("Person23333333333333333333333");
		pr.save(p2);
	}
	
	public void getAge(Integer id){
		Person p = pr.findById(id);
		Integer age = p.getAge();
		if(age <= 10) {
			throw new PersonAgeException(HttpResultEnum.PRIM_SCHOOL);
		}else{
			throw new PersonAgeException(HttpResultEnum.MID_SCHOOL);
		}
	}
	
	public Person findOne(Integer id) {
		return pr.findOne(id);
	}
	
	public List<Person> ascPersons(){
		return pr.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public List<Person> descPersons(){
		return pr.findAll(new Sort(Sort.Direction.DESC, "id"));
	}
}
