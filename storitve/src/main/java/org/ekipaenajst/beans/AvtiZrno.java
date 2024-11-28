package org.ekipaenajst.beans;

import org.ekipaenajst.entitete.Avto;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@RequestScoped // js literally sam kradem kodo iz UporabnikiZrno
public class AvtiZrno {
    private EntityManagerFactory emf;

    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManager em;


    private Logger log = Logger.getLogger(AvtiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + AvtiZrno.class.getSimpleName());

        emf = Persistence.createEntityManagerFactory(
                "accounts-jpa"
        );

        em = emf.createEntityManager();


    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + AvtiZrno.class.getSimpleName());

        em.close();
        emf.close();
    }

    public List<Avto> getAvti() {

        //em.getTransaction().begin();
        Query q = em.createNamedQuery("Avto.findAll", Avto.class);

        List<Avto> resultList = (List<Avto>)q.getResultList();

        return resultList;
    }
}
