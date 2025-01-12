package org.ekipaenajst.beans;

import org.ekipaenajst.entitete.Avto;
import org.ekipaenajst.entitete.Uporabnik;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped // js literally sam kradem kodo iz UporabnikiZrno
public class AvtiZrno implements Serializable { // BAJE JE DOBRO DA ZRNA IMPLEMENTIRAJO SERIALIZABLE, TAKO DA SEM DODAL

    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManager em;


    private Logger log = Logger.getLogger(AvtiZrno.class.getName());

    @Transactional
    public List<Avto> getAvti() {

        //em.getTransaction().begin();
        Query q = em.createNamedQuery("Avto.findAll", Avto.class);

        List<Avto> resultList = (List<Avto>)q.getResultList();

        return resultList;
    }


    @Transactional
    public List<Avto> getAvtiByOwner(int id) {

        //em.getTransaction().begin();
//        Query q = em.createNamedQuery("Avto.findByOwner", Avto.class);
//        q.setParameter("ownerParam", id);

       // List<Avto> resultList = (List<Avto>)q.getResultList();

        List<Avto> resultList = getAvti();
        List<Avto> resultList2 = new ArrayList<Avto>();

        for (Avto avto : resultList) {
            if (avto.getLastnik().getId() == id) {
                resultList2.add(avto);
                System.out.println(avto);
            }
        }



        return resultList2;
    }

    @Transactional //DEBUG
    public Avto getAvto(int id) {
        return em.find(Avto.class, id);
    }


    @Transactional
    public void updateAvto(Avto avto) {

        em.merge(avto);

    }
}
