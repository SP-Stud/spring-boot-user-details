package UserDetails.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import UserDetils.model.User;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	     
	    private static List<User> users;
	     
	    static {
	        users= populateDummyUsers();
	    }
	 
	    public List<User> findAllUsers() {
	        return users;
	    }
	     
	    public User findById(String id) {
	        for(User user : users){
	            if(user.getId().equalsIgnoreCase(id) && user.isActive()){
	                return user;
	            }
	        }
	        return null;
	    }
	     
	     
	    public void saveUser(User user) {
	        user.setActive(true);
	        users.add(user);
	    }
	 
	    public void updateUser(User user) {
	        int index = users.indexOf(user);
	        users.set(index, user);
	    }
	 
	 
	    public boolean isUserExist(User user) {
//	    	if(user.getEmail()!=null || user.getEmail()==""){
	        return findByName(user.getEmail())!=null;
//	    	}
//	    	return true;
	    }
	    
	    public User findByName(String email) {
	        for(User user : users){
	            if(user.getEmail().equalsIgnoreCase(email) && !user.isActive()){
	                return user;
	            }
	        }
	        return null;
	    }
	    
	    public boolean isUserIdExist(User user) {
	    	if(user.getId()!=null){
	        return findById(user.getId())!=null;
	    	}
	    	return false;
	    }
	    
	    //dummy data created
	     
	    private static List<User> populateDummyUsers(){
	        List<User> users = new ArrayList<User>();
	       
	        users.add( new User("123%&23","John", "Smith" ,"john.smith@gmail.com", 123456, "02/05/1000" ,true));
	        users.add(new User("123%&23ww","John1", "Smith" ,"john1.smith@gmail.com", 123456, "02/05/1000" ,true));
	        users.add(new User("123%&23qq","John2", "Smith" ,"john2.smith@gmail.com", 123456, "02/05/1000" ,true));
	        users.add(new User("ABC","ABC", "ASD" ,"APQ.PAS@ff.com", 444456, "02-11-1999" ,true));
	        return users;
	    }
	    
	    public Date StringToDateConverter( String Birthdate) throws ParseException{
	      
	        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(Birthdate); 
	        return date;
	    }
	    
	 
	}
