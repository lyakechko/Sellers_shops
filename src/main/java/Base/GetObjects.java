package Base;

import Base.DBO.Seller;
import Base.DBO.Shop;
import Base.HibernateUtil.HibernateEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GetObjects {
    private static EntityManager entityManager = HibernateEntityManager.getEntityManager();

    public static Set<Seller> getSellers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Seller> personCriteria = cb.createQuery(Seller.class);
        Root<Seller> personRoot = personCriteria.from(Seller.class);
        personCriteria.select(personRoot);
        return new HashSet<>(entityManager.createQuery(personCriteria).getResultList());
    }

    public static List<Shop> getShopFromDB() {
        entityManager.getTransaction().begin();
        return entityManager.createQuery("from Shop", Shop.class).getResultList();
    }

    public static Set<Shop> getShops() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> personCriteria = cb.createQuery(Shop.class);
        Root<Shop> personRoot = personCriteria.from(Shop.class);
        personCriteria.select(personRoot);
        return new HashSet<>(entityManager.createQuery(personCriteria).getResultList());
    }

    public static void getShopPage() {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> personCriteria = cb.createQuery(Shop.class);
        entityManager.createQuery(personCriteria).getResultList().forEach(System.out::println);
    }

    public static void saveShops(List<Shop> shops) {
        EntityManager manager = HibernateEntityManager.getEntityManager();
        for (Shop shop : shops) {
            manager.getTransaction().begin();
            manager.persist(shop);
            manager.getTransaction().commit();
        }
    }

    public static void saveSellers(Set<Seller> sellers) {
        EntityManager manager = HibernateEntityManager.getEntityManager();
        for (Seller seller : sellers) {
            manager.getTransaction().begin();
            manager.persist(seller);
            manager.getTransaction().commit();
        }
    }

    public static void updateSellers(Set<Seller> sellers) throws InterruptedException {
        int i = 0;
        EntityManager manager = HibernateEntityManager.getEntityManager();
        for (Seller seller : sellers) {
            System.out.println("----------------");
            manager.merge(seller);
            System.out.println("ОБНОВИЛИ");
        }
    }

    public static void updateShops(Set<Shop> shops) {
        for (Shop shop : shops) {
            EntityManager manager = HibernateEntityManager.getEntityManager();
            System.out.println("----------------");
            manager.merge(shop);
        }
    }
}