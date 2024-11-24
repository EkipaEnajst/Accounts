package org.ekipaenajst.entitete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="naslov")
public class Naslov {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="mesto")
    private String mesto;

    @Column(name="ulica")
    private String ulica;

    @Column(name="hisnaSt")
    private String hisnaSt;

    @Column(name="postnaSt")
    private String postnaSt;
}
