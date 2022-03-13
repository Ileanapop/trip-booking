package interfaces;

import api.models.UserModel;
import api.models.VacationPackageModel;

import java.util.List;

public interface IUserService {

    void insertUser(String firstName,String lastName, String email, String username, String password);
    UserModel authenticateUser(String username, String password);
    boolean createBooking(UserModel user, VacationPackageModel packageModel);
    //List<VacationPackageModel> getUserBookings(String user_id);

}
