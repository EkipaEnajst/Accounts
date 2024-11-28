package org.ekipaenajst.beans;

import org.ekipaenajst.entitete.Naslov;
import org.ekipaenajst.entitete.Uporabnik;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NasloviZrno { //me when i copypaste
    private EntityManagerFactory emf;

    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManager em;

    private Logger log = Logger.getLogger(NasloviZrno.class.getName());




    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + NasloviZrno.class.getSimpleName());

        emf = Persistence.createEntityManagerFactory(
                "accounts-jpa"
        );

        em = emf.createEntityManager();


    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + NasloviZrno.class.getSimpleName());

        em.close();
        emf.close();
    }

    public List<Naslov> getNaslovi() {

        //em.getTransaction().begin();
        Query q = em.createNamedQuery("Naslov.findAll", Naslov.class);

        List<Naslov> resultList = (List<Naslov>)q.getResultList();

        return resultList;
    }
}
