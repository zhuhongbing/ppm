package com.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	private Set<People> peopleList =  new LinkedHashSet<People>();;
	
	@ManyToMany(targetEntity =com.hibernate.model.People.class,
			cascade={CascadeType.ALL} )
	@JoinTable(name="e_p",
	joinColumns=@JoinColumn(name="event_id",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="people_id",referencedColumnName="id"))
	public Set<People> getPeopleList() {
		return peopleList;
	}
	public void setPeopleList(Set<People> peopleList) {
		this.peopleList = peopleList;
	}
	/*
	private Set<Event> eventList = new LinkedHashSet<Event>();
	

	@ManyToMany(cascade={CascadeType.ALL} )
	@JoinTable(name="e_e")
	public Set<Event> getEventList() {
		return eventList;
	}
	
	public void setEventList(Set<Event> eventList) {
		this.eventList = eventList;
	}
*/	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String title;
	private Date date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Event() {
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}