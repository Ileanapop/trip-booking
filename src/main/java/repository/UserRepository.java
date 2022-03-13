package repository;

import entity.TravelAgency.Destination;
import entity.TravelAgency.VacationPackage;
import entity.Users.User;
import interfaces.IUserRepository;
import interfaces.IVacationPackageService;
import service.VacationPackageService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("ro.assignment1.lab.SD");

    public void insertNewUser(String id, String firstName,String lastName, String email,String username, String password){

        User user = new User(id, firstName, lastName, email,username, password);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> selectAllUsers(){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public User getUserById(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public boolean createBooking(String user_id, String package_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class,user_id);

        VacationPackage vacationPackage = em.find(VacationPackage.class,package_id);
        vacationPackage.setBookings(vacationPackage.getBookings() + 1);
        IVacationPackageService vacationPackageService = new VacationPackageService();
        vacationPackageService.editVacationPackage(vacationPackage.getId(), vacationPackage.getName(), vacationPackage.getPrice(),
                vacationPackage.getStartDate(),vacationPackage.getEndDate(),vacationPackage.getExtraSpecifications(),vacationPackage.getPeopleCapacity());

        user.getPackages().add(vacationPackage);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public List<VacationPackage> getUserBookings(String user_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, user_id);
        return  user.getPackages();
    }

}
