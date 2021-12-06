package com.freestack.evaluation;

import javax.persistence.*;

@Entity
@Table(name = "uber_conducteur")
public class UberDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="disponibilite")
    private boolean available;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    //hibernate a besoin d'un constructeur vide
public UberDriver(){

}

    public UberDriver( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public Long getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
