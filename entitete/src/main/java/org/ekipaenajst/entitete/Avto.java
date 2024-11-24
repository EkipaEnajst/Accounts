package org.ekipaenajst.entitete;

import javax.persistence.*;

@Entity
@Table(name="avto")
public class Avto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="znamka")
    private String znamka;

    @Column(name="letnik")
    private String letnik;

    @Column(name="model")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastnikId")
    private Uporabnik lastnik;
}
