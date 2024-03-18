package com.zsirosdeszkasok.wedding.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String name;
	@ManyToOne
	private Family family;
	private Boolean hasAccepted;
	@OneToMany(mappedBy = "person")
	private List<PersonChange> changeHistory;
	

	public Person(String name, Family family, Boolean hasAccepted) {
		this.id = null;
		this.name = name;
		this.family = family;
		this.hasAccepted = hasAccepted;
		this.changeHistory = new ArrayList<>();
	}
	public Person() {
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Family getFamily() {
		return family;
	}

	public Boolean getHasAccepted() {
		return hasAccepted;
	}

	public List<PersonChange> getChangeHistory() {
		return changeHistory;
	}

	public void setHasAccepted(Boolean hasAccepted) {
		this.hasAccepted = hasAccepted;
	}
}
