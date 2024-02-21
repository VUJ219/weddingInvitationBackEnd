package com.zsirosdeszkasok.wedding.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "family")
	private List<Person> members;
	private String comment;

    public Family(List<Person> members, String comment) {
        this.id = null;
        this.members = members;
        this.comment = comment;
    }
    public Family() {
    }

    public Integer getId() {
        return id;
    }

    public List<Person> getMembers() {
        return members;
    }

    public String getComment() {
        return comment;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
