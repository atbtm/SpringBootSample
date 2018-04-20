package com.atbtm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atbtm.domain.Person;
import com.atbtm.service.PersonService;

import org.junit.Assert; 

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
	@Autowired
	private PersonService ps;
	
	@Test
	public void findOneTest() {
		Person p = ps.findOne(2);
		Assert.assertEquals(new Integer(23), p.getAge());
	}
}
