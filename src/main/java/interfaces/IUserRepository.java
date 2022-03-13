package interfaces;

import entity.TravelAgency.VacationPackage;
import entity.Users.User;

import java.util.List;

public interface IUserRepository {

    void insertNewUser(String id, String firstName,String lastName, String email,String username, String password);
    List<User> selectAllUsers();
    User getUserById(String id);
    boolean createBooking(String user_id, String package_id);
    //List<VacationPackage> getUserBookings(String user_id);
}
