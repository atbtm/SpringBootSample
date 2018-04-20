package com.atbtm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atbtm.domain.HttpResult;
import com.atbtm.domain.Person;
import com.atbtm.property.BfProperties;
import com.atbtm.repository.PersonRepository;
import com.atbtm.service.PersonService;
import com.atbtm.utils.HttpResultUtil;

@Controller
public class PersonController {
	@Autowired
	private PersonRepository pr;
	
	@Autowired
	private PersonService ps;
	
	@Autowired
	private BfProperties bfp;
	
	@GetMapping(value="/bfProps")
	public String getBfProps() {
		return bfp.toString();
	}
	
	
	@GetMapping(value="/persons")
	public List<Person> getPersonList(){
		return pr.findAll();
	}
	
	@GetMapping(value="/person")
	//request param value default to match with var name.
	public List<Person> personByName(@RequestParam String name){
		return pr.findByName(name);
	}
	
	//如果直接传入Person，spring会自动把fields封装好Person传入。直接存就口以
	@PostMapping(value="/person")
	public HttpResult<Person> addPerson(@Valid Person person, BindingResult br,
			@Autowired
			HttpResultUtil hru) {
		if(br.hasErrors()) {
			return null;
			//return hru.failResult(br.getFieldError().getDefaultMessage(), 1);
		}
		pr.save(person);
		return hru.successResult(person);
	}
	
	@GetMapping(value="/person/{id}")
	public Person getPerson(@PathVariable(value="id") Integer id){
		return pr.findOne(id);
	}
	
	@PutMapping(value="/person/{id}")
	public Person updatePerson(@PathVariable(value="id") Integer id, @RequestParam(value="age") Integer age,
			@RequestParam(value="name") String name) {
		Person p = pr.findOne(id);
		p.setAge(age);
		p.setName(name);
		pr.save(p);
		return p;
	}
	
	@DeleteMapping(value="/person/{id}")
	public void deletePerson(@PathVariable(value="id") Integer id) {
		Person p = pr.getOne(id);
		pr.delete(p);
	}
	
	@PostMapping(value="/addTwo")
	public void addTwo() {
		ps.addTwo();
	}
	
	@GetMapping(value="/person/getAge/{id}")
	public void getAge(@PathVariable("id") Integer id){
		ps.getAge(id);
	}
	
	@GetMapping("/persons/asc")
	public List<Person> getPersonsInAsc() {
		return ps.ascPersons();
	}
	
	@GetMapping("/persons/desc")
	public List<Person> getPersonsInDesc() {
		return ps.descPersons();
	}
	
	@RequestMapping("/home")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "home";
	}
}
