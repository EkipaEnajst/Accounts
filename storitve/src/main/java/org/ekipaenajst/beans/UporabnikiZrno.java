package org.ekipaenajst.beans;

import org.ekipaenajst.entitete.Uporabnik;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped // probably... mogoče treba spremeniti
public class UporabnikiZrno {
    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {

        // TODO implementiraj uporabo NamedQueryja
        return null;
    }


}
