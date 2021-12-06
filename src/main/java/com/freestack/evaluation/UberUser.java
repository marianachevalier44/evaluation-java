package com.freestack.evaluation;

import javax.persistence.*;

@Entity
@Table(name = "uber_utilisateur")
public class UberUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public UberUser() {
    }

    public UberUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
