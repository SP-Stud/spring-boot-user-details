package UserDetails.services;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import UserDetils.model.User;

public interface UserDetailsService {
	
	User findById(String id);
    
    User findByName(String name);
     
    void saveUser(User user);
     
    void updateUser(User user);
 
    List<User> findAllUsers();
     
    boolean isUserExist(User user);
    
    public boolean isUserIdExist(User user);
    
    public Date StringToDateConverter( String Birthdate)throws ParseException;

}
