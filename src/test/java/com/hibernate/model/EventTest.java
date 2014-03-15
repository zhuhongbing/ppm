package com.hibernate.model;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.hibernate.util.HibernateUtil;

public class EventTest {

	@Test
	public void schemaTest() {
		SchemaExport se = new SchemaExport(new Configuration().configure());
		se.create(true, true);
		
//		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void saveEventTest(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle("JohnDeere Event");
        theEvent.setDate(new Date());
        People p = new People();
        p.setName("Jacky");
//        theEvent.setPeople(p);
//        session.save(p);
        session.save(theEvent);

        session.getTransaction().commit();
	}

}
