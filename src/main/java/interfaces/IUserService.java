package interfaces;

import api.models.UserModel;

public interface IUserService {

    void insertUser(String firstName,String lastName, String email, String username, String password);
    UserModel authenticateUser(String username, String password);

}
