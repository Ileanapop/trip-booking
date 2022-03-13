package repository;

import Util.ExceptionHandler.OperationStatus;
import Util.ExceptionHandler.OperationSuccess;
import entity.TravelAgency.Destination;
import entity.TravelAgency.VacationPackage;
import entity.Users.User;
import interfaces.IVacationPackageRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class VacationPackageRepository implements IVacationPackageRepository {

    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("ro.assignment1.lab.SD");

    @Override
    public OperationStatus addVacationPackage(String id, String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity, int bookings, Destination destination) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vacationPackage = new VacationPackage(id,name,price,startDate,endDate,extraSpecifications,capacity,bookings,destination);
        em.persist(vacationPackage);
        em.getTransaction().commit();
        em.close();
        return new OperationSuccess();
    }


    @Override
    public OperationStatus editVacationPackage(String id,String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vacationPackage = em.find(VacationPackage.class,id);
        vacationPackage.setName(name);
        vacationPackage.setPrice(price);
        vacationPackage.setStartDate(startDate);
        vacationPackage.setEndDate(endDate);
        vacationPackage.setExtraSpecifications(extraSpecifications);
        vacationPackage.setPeopleCapacity(capacity);
        em.merge(vacationPackage);
        em.getTransaction().commit();
        em.close();
        return new OperationSuccess();
    }

    @Override
    public List<VacationPackage> selectVacationPackages() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM VacationPackage p");
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public OperationStatus deleteVacationPackage(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vacationPackage = em.find(VacationPackage.class,id);
        em.remove(vacationPackage);
        for(User user: vacationPackage.getUser()){
            user.getPackages().remove(vacationPackage);
            em.persist(user);
        }
        em.getTransaction().commit();
        em.close();
        return new OperationSuccess();
    }

}
