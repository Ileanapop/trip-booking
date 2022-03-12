package interfaces;

public interface IUserService {

    void insertUser(String firstName,String lastName, String email, String username, String password);
    boolean authenticateUser(String username, String password);
}
