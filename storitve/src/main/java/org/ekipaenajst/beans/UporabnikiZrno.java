package org.ekipaenajst.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.ekipaenajst.entitete.Avto;
import org.ekipaenajst.entitete.Uporabnik;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped // probably... mogoče treba spremeniti
public class UporabnikiZrno {

    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManager em;

    @Inject
    private AvtiZrno avtiZrno;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());



// INICIALIZACIJA IN DEINICIALIZACIJA MORDA NEPOTREBNA, KER ENTITYMANAGER UPRAVLJA ŽE JTA CONTAINER
//    @PostConstruct
//    private void init() {
//        log.info("Inicializacija zrna " + UporabnikiZrno.class.getSimpleName());
//
//        emf = Persistence.createEntityManagerFactory(
//                "accounts-jpa"
//        );
//
//        em = emf.createEntityManager();
//
//
//    }
//
//    @PreDestroy
//    private void destroy() {
//        log.info("Deinicializacija zrna " + UporabnikiZrno.class.getSimpleName());
//
//        em.close();
//        emf.close();
//    }



    @Transactional // DEBUG
    public List<Uporabnik> getUporabniki(QueryParameters query) {

        System.out.println(query.getFields());
        System.out.println(query.getFilters());

        //Query q = em.createNamedQuery("Uporabnik.findAll", Uporabnik.class);

        List<Uporabnik> uporabniki = JPAUtils.queryEntities(em, Uporabnik.class, query);
        return uporabniki;

        //List<Uporabnik> resultList = (List<Uporabnik>)q.getResultList();

        //return resultList;
    }

    @Transactional //DEBUG
    public Uporabnik getUporabnik(int id) {
        return em.find(Uporabnik.class, id);
    }

    @Transactional
    public Uporabnik getUporabnikByEmailAndPassword(Uporabnik uporabnik) {
        String email = uporabnik.getEmail();
        String password = uporabnik.getPassword();

        Query q = em.createNamedQuery("Uporabnik.findByEmailAndPassword");
        q.setParameter("email", email);
        q.setParameter("password", password);
        List<Uporabnik> uporabniki = q.getResultList();
        if (uporabniki.isEmpty()) {
            return null;
        }

        return uporabniki.get(0);

    }

    @Transactional //DEBUG
    public Uporabnik getUporabnikByName(String firstName, String lastName) {
        Query q = em.createNamedQuery("Uporabnik.findByName", Uporabnik.class);
        q.setParameter("firstNameParam", firstName);
        q.setParameter("lastNameParam", lastName);

        List<Uporabnik> resultList = (List<Uporabnik>)q.getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
    @Transactional
    public void updateUporabnik(Uporabnik uporabnik) {

        em.merge(uporabnik);

    }
    @Transactional
    public void deleteUporabnik(Uporabnik uporabnik) {

        Uporabnik u = em.find(Uporabnik.class, uporabnik.getId());
        em.remove(u);
    }

    @Transactional
    public Uporabnik createUporabnik(Uporabnik uporabnik) {

        try {

            if (getUporabnikByEmailAndPassword(uporabnik)==null){
                em.persist(uporabnik);
                return uporabnik;
            }

            return null;


        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Creating not work");
        }

        return null;

    }

    // NON-CRUD methods

    @Transactional
    public void addAvtoToUporabnik(Uporabnik uporabnik, Avto avto) {
        uporabnik.addAvto(avto);
        updateUporabnik(uporabnik);

        avto.setLastnik(uporabnik);
        avtiZrno.updateAvto(avto);
    }

    


}
