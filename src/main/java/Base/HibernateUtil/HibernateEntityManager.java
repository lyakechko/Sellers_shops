package Base.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateEntityManager {

    private static final String PERSISTENT_UNIT_NAME = "item-manager-pu";

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
