package com.hibernate.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class People implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;

	private Set<Event> eventList = new LinkedHashSet<Event>();;

	@ManyToMany(targetEntity=com.hibernate.model.Event.class,
			cascade={CascadeType.ALL},
			mappedBy="peopleList")
//	@JoinTable(name = "e_p", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "people_id"))
//	@Cascade ({CascadeType.ALL})
	public Set<Event> getEventList() {
		return eventList;
	}

	public void setEventList(Set<Event> eventList) {
		this.eventList = eventList;
	}


	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
