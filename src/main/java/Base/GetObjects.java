package Base;

import Base.DBO.Seller;
import Base.DBO.Shop;
import Base.HibernateUtil.HibernateEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

public class GetObjects {
    public static List<Seller> getSellersFromDB() {
        EntityManager entityManager = HibernateEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.createQuery("from Seller", Seller.class).getResultList();
    }

    public static List<Shop> getShopFromDB() {
        EntityManager entityManager = HibernateEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.createQuery("from Shop", Shop.class).getResultList();
    }
}
