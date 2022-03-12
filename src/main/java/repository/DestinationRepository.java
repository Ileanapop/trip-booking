package repository;

import entity.TravelAgency.Destination;
import entity.TravelAgency.PopularityLevel;
import interfaces.IDestinationRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DestinationRepository implements IDestinationRepository {

    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("ro.assignment1.lab.SD");

    @Override
    public List<Destination> getDestinations() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT d FROM Destination d");
        return query.getResultList();
    }

    @Override
    public boolean deleteDestination(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Destination WHERE id = id");
        int row = query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return row!=0;
    }

    @Override
    public void addDestination(String id, String location, String country, int ratingStars, PopularityLevel popularityLevel) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Destination destination = new Destination(id,location,country,ratingStars,popularityLevel);
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

}
