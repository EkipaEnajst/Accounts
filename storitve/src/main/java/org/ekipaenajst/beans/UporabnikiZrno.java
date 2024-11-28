package org.ekipaenajst.beans;

import org.ekipaenajst.entitete.Uporabnik;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped // probably... mogoče treba spremeniti
public class UporabnikiZrno {
    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManagerFactory emf;
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());




    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UporabnikiZrno.class.getSimpleName());

        emf = Persistence.createEntityManagerFactory(
                "accounts-jpa"
        );

        em = emf.createEntityManager();


    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UporabnikiZrno.class.getSimpleName());

        em.close();
        emf.close();
    }

    public List<Uporabnik> getUporabniki() {

        em.getTransaction().begin();
        Query q = em.createNamedQuery("Uporabnik.findAll", Uporabnik.class);

        List<Uporabnik> resultList = (List<Uporabnik>)q.getResultList();

        return resultList;
    }


}
