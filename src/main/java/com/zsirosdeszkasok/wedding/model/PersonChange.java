package com.zsirosdeszkasok.wedding.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "person_change")
public class PersonChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private FamilyChange familyChange;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Family family;
    private Instant changeDate;
    private Boolean newHasAccepted;
    private Boolean wasCreated;

    public PersonChange(FamilyChange familyChange, Person person, Family family, Instant changeDate, Boolean newHasAccepted, Boolean wasCreated) {
        this.id = null;
        this.familyChange = familyChange;
        this.person = person;
        this.family = family;
        this.changeDate = changeDate;
        this.newHasAccepted = newHasAccepted;
        this.wasCreated = wasCreated;
    }

    public Integer getId() {
        return id;
    }

    public FamilyChange getFamilyChange() {
        return familyChange;
    }

    public Person getPerson() {
        return person;
    }

    public Family getFamily() {
        return family;
    }

    public Instant getChangeDate() {
        return changeDate;
    }

    public Boolean getNewHasAccepted() {
        return newHasAccepted;
    }

    public Boolean getWasCreated() {
        return wasCreated;
    }
}
