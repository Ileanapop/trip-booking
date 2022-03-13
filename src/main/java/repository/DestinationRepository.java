package repository;

import entity.TravelAgency.Destination;
import entity.TravelAgency.PopularityLevel;
import entity.TravelAgency.VacationPackage;
import interfaces.IDestinationRepository;
import interfaces.IVacationPackageService;
import service.VacationPackageService;

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

    public Destination getDestinationById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT d FROM Destination d where d.id = :ID");
        query.setParameter("ID", id);
        return (Destination) query.getResultList().get(0);
    }

    @Override
    public boolean deleteDestination(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        //Query query = em.createQuery("SELECT d FROM Destination d where d.id = :ID");
        //query.setParameter("ID", id);
        //Destination destination =(Destination) query.getResultList().get(0);
        Destination destination = em.find(Destination.class,id);
        //em.remove(destination);
        IVacationPackageService vacationPackageService = new VacationPackageService();
        for(VacationPackage vacationPackage: destination.getVacationPackageList()){
            vacationPackageService.deleteVacationPackage(vacationPackage.getId());
        }
        Query query = em.createQuery("DELETE FROM Destination where id = :ID");
        query.setParameter("ID", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return true;
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
