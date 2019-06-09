package hu.nl.hibernate.domain;

import javax.persistence.*;

@Entity
@Table(name= "GEBRUIKER")
public class Gebruiker {
    @Id
    private int id;

    @Column
    private String naam;

    public Gebruiker() { }

    public Gebruiker(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}

