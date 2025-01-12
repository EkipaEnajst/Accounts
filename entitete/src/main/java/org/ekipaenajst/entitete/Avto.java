package org.ekipaenajst.entitete;

import javax.persistence.*;

@Entity
@Table(name="avto")
@NamedQueries(value = {
        @NamedQuery(name = "Avto.findAll", query="SELECT a FROM Avto a"),
        @NamedQuery(name = "Avto.findId", query="SELECT a from Avto a WHERE a.id= :idParam"),
        @NamedQuery(name = "Avto.findByBrand", query="SELECT a FROM Avto a WHERE a.znamka= :brandParam ORDER BY a.znamka"),
        @NamedQuery(name = "Avto.findByModel", query="SELECT a FROM Avto a WHERE a.model= :modelParam ORDER BY a.model"),
        @NamedQuery(name = "Avto.findByOwner", query="SELECT a FROM Avto a WHERE a.lastnik.id = :ownerIdParam"),
        @NamedQuery(name = "Avto.findByTimeframe",
                query="SELECT a FROM Avto a WHERE a.letnik>= :beforeParam AND a.letnik<= :beforeParam")
})
public class Avto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    //@Column(name="znamka")
    private String znamka;

    //@Column(name="letnik")
    private String letnik;

    //@Column(name="model")
    private String model;

    private String registrskaStevilka;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "lastnik")
    private Uporabnik lastnik;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZnamka() {
        return znamka;
    }

    public void setZnamka(String znamka) {
        this.znamka = znamka;
    }

    public String getLetnik() {
        return letnik;
    }

    public void setLetnik(String letnik) {
        this.letnik = letnik;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Uporabnik getLastnik() {
        return lastnik;
    }

    public void setLastnik(Uporabnik lastnik) {
        this.lastnik = lastnik;
    }

    public String getRegistrskaStevilka() {
        return registrskaStevilka;
    }

    public void setRegistrskaStevilka(String registrskaStevilka) {
        this.registrskaStevilka = registrskaStevilka;
    }
}
