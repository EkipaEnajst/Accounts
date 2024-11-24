package org.ekipaenajst.beans;

import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;


@RequestScoped // probably... mogoče treba spremeniti
public class AvtiZrno {
    @PersistenceContext(unitName = "accounts-jpa")
    private EntityManager em;


}
