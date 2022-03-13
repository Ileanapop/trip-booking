package service;

import Util.Mapper;
import api.models.DestinationModel;
import api.models.UserModel;
import api.models.VacationPackageModel;
import com.sun.xml.bind.v2.model.core.ID;
import entity.TravelAgency.VacationPackage;
import entity.Users.User;
import interfaces.IDestinationService;
import interfaces.IUserService;
import interfaces.IVacationPackageService;
import repository.UserRepository;
import service.validators.UserData.EmailValidator;
import service.validators.UserData.UsernameValidator;

import java.util.ArrayList;
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

    @Override
    public boolean createBooking(UserModel user, VacationPackageModel vacationPackageModel) {
        return userRepository.createBooking(user.getId(), vacationPackageModel.getId());
    }


    @Override
    public List<VacationPackageModel> getUserBookings(String user_id) {
        return Mapper.packageEntityListToPackageModelList(userRepository.getUserBookings(user_id));
    }

}
