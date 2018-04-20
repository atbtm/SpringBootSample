package com.atbtm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atbtm.property.BfProperties;

@RestController
public class HelloController {
	@Autowired
	private BfProperties bfProp;
	
	//http://localhost:8080/hello/123
	@GetMapping(value= {"/hello/{id}", "/hi"})
	public String say(@PathVariable("id") Integer id) {
		return "Hello you, \nAge: "+bfProp.getAge()
		+ "\nid: "+id;
	}
	

	//http://localhost:8080/helloParam?id=123
	@GetMapping(value= {"/helloParam"})
	public String sayParam(@RequestParam("id") Integer id) {
		return "Hello you, \nAge: "+bfProp.getAge()
		+ "\nid: "+id;
	}
	
	//http://localhost:8080/helloParamDefault    without passing in id, default value is returned
	@GetMapping(value= {"/helloParamDefault"})
	public String sayParamDefault(@RequestParam(value="id", required=false, defaultValue="1") Integer id) {
		return "Hello you, \nAge: "+bfProp.getAge()
		+ "\nid: "+id;
	}
	
	@PostMapping(value= {"/hello", "/hi"})
	public String postSay() {
		return "Hello you, \nAge: "+bfProp.getAge();
	}
}
