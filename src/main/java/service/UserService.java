package service;

import Util.Mapper;
import api.models.UserModel;
import entity.Users.User;
import interfaces.IUserService;
import repository.UserRepository;
import service.validators.UserData.EmailValidator;
import service.validators.UserData.UsernameValidator;

import java.util.List;

public class UserService implements IUserService {

    private UserRepository userRepository = new UserRepository();

    public void insertUser(String firstName,String lastName, String email, String username, String password){

        EmailValidator emailValidator = new EmailValidator();
        UsernameValidator usernameValidator = new UsernameValidator();

        List<User> users = userRepository.selectAllUsers();

        if(emailValidator.validateEmail(email) && usernameValidator.checkUniqueUsername(users,username)){
            userRepository.insertNewUser(IDGeneratorService.generateUniqueID(), firstName, lastName, email,username, password);
        }
        else{
            System.out.println("Cannot insert user");
        }

    }

    public UserModel authenticateUser(String username, String password){
        List<User> users = userRepository.selectAllUsers();

        for(User user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return Mapper.entityUserToModelUser(user);
        }
        return null;
    }

}
