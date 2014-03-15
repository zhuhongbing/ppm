package com.hibernate.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hibernate.dao.impl.EventRepository;
import com.hibernate.model.Event;
import com.hibernate.model.People;

@ContextConfiguration(locations = { "classpath:bean.xml" })
public class EventServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
//	EventService eventService;
	EventRepository er;


	@Test
	public void testSaveEvent() {
//		People p = peopleService.findById(People.class, 2);
//		People p = new People();
//		p.set
//		p.setName("Jacky12");
//		Set<People> pList = new HashSet<People>();
		Event em = new Event();
		em.setDate(new Date());
		em.setName("JohnDeere Event13");
		em.setTitle("No Title");
		for(int i=0;i<10;i++){
			/*Event e = new Event();
			e.setDate(new Date());
			e.setName("name"+i);
			e.setTitle("tile"+i);
			e.getEventList().add(em);
			em.getEventList().add(e);*/
			People p = new People();
			p.setName("Name"+i);
			p.getEventList().add(em);
			em.getPeopleList().add(p);
//			pList.add(p);
			
		}
//		em.setPeopleList(pList);
//		eventService.insert(em);
		er.save(em);
		// fail("Not yet implemented");
	}

}
