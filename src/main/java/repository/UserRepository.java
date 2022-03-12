package repository;

import entity.Users.User;
import interfaces.IUserRepository;

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

}
