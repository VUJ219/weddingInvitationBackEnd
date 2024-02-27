package com.zsirosdeszkasok.wedding.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "family_change")
public class FamilyChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Family family;
    @OneToMany(mappedBy = "familyChange")
    private List<PersonChange> personChanges;
    private String newComment;
    private Instant changeTime;

    public FamilyChange(Family family, List<PersonChange> personChanges, String newComment, Instant changeTime) {
        this.id = null;
        this.family = family;
        this.personChanges = personChanges;
        this.newComment = newComment;
        this.changeTime = changeTime;
    }
    public FamilyChange() {
    }

    public Integer getId() {
        return id;
    }

    public Family getFamily() {
        return family;
    }

    public List<PersonChange> getPersonChanges() {
        return personChanges;
    }

    public String getNewComment() {
        return newComment;
    }

    public Instant getChangeTime() {
        return changeTime;
    }
}
