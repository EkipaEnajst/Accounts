package org.ekipaenajst.entitete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "uporabnik")
@NamedQueries(value = {
        @NamedQuery(name = "Uporabnik.findAll", query = "SELECT u FROM Uporabnik u"),
        @NamedQuery(name = "Uporabnik.findId", query = "SELECT u from Uporabnik u WHERE u.id= :idParam"),
        @NamedQuery(name = "Uporabnik.findByLastname", query = "SELECT u FROM Uporabnik u WHERE u.lastName= :lastNameParam ORDER BY u.lastName"),
        @NamedQuery(name = "Uporabnik.findByName",
                query = "SELECT u FROM Uporabnik u WHERE u.lastName = :lastNameParam AND u.firstName = :firstNameParam"),
        @NamedQuery(name = "Uporabnik.findByEmail", query = "SELECT u FROM Uporabnik u WHERE u.email = :emailParam ORDER BY u.email"),
        @NamedQuery(name = "Uporabnik.findByEmailAndPassword", query = "SELECT u FROM Uporabnik u WHERE u.email = :emailParam AND u.password = :passwordParam ORDER BY u.lastName")

})
public class Uporabnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(name = "firstName")
    private String firstName;

    //@Column(name = "lastName")
    private String lastName;

    //@Column(name = "email")
    private String email;

    //@Column(name = "username")
    private String username;

    private String password;

    @OneToOne
    //@JoinColumn(name = "naslovId")
    private Naslov naslov;

    @OneToMany(fetch = FetchType.LAZY/*, mappedBy = "uporabnik"*/)
    private List<Avto> avti;

    public Uporabnik() {
        this.avti = new ArrayList<Avto>(); // REMOVE THIS LINE IF CODE DOESNT WORK
    }

    @Override
    public String toString() {
        return String.format("ID: %d Ime: %s Priimek: %s Email: %s\n", this.id, this.firstName, this.lastName, this.email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Naslov getNaslov() {
        return naslov;
    }

    public void setNaslov(Naslov naslov) {
        this.naslov = naslov;
    }

    public void addAvto(Avto avto) {
        this.avti.add(avto);
    }

    public void removeAvto(Avto avto) {
        this.avti.remove(avto);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
