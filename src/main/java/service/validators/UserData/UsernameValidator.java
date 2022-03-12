package service.validators.UserData;

import entity.Users.User;

import java.util.List;

public class UsernameValidator{

    public boolean checkUniqueUsername(List<User> users, String username){
        for(User user: users){
            if(user.getUsername().equals(username))
                return false;
        }
        return true;
    }
}
