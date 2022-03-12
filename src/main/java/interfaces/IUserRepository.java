package interfaces;

import entity.Users.User;

import java.util.List;

public interface IUserRepository {

    void insertNewUser(String id, String firstName,String lastName, String email,String username, String password);
    List<User> selectAllUsers();

}
