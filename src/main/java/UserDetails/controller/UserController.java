package UserDetails.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UserDetails.services.UserDetailsService;
import UserDetais.Util.CustomErrorType;
import UserDetils.model.User;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	
	// -------------------Create a User-------------------------------------------
	 
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) throws ParseException {
    	
    	if(user.getId()=="" || user.getId() ==null){
    		return new ResponseEntity(new CustomErrorType("User Id can not be blank or nulls "),HttpStatus.NOT_FOUND);
    		
    	}
    	if(user.getId()=="" || user.getEmail() ==null){
    		return new ResponseEntity(new CustomErrorType("User Email can not be blank or nulls "),HttpStatus.NOT_FOUND);
    		
    	}
    	
    	Date date=userDetailsService.StringToDateConverter(user.getBirthDate());
    	
    	if(userDetailsService.isUserIdExist(user)){
    		
    		return new ResponseEntity(new CustomErrorType("Unable to create. A User Id " + 
    	            user.getId() + " already exist."),HttpStatus.CONFLICT);
    	}
        if (userDetailsService.isUserExist(user)) {

            return new ResponseEntity(new CustomErrorType("Unable to create. A User with emailId " + 
            user.getEmail() + " already exist."),HttpStatus.CONFLICT);
        }
        userDetailsService.saveUser(user);
 
        return new ResponseEntity<String>(user.getId(), HttpStatus.CREATED);
    }
    
  // ------------------- Update a User ------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) {
      
	  if(user.getId()=="" || user.getId() ==null){
  		return new ResponseEntity(new CustomErrorType("User Id can not be blank or nulls "),HttpStatus.NO_CONTENT);
  		
  	}
      User currentUser = userDetailsService.findById(id);

      if (currentUser == null) {
          return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found or deactivated."),
                  HttpStatus.NOT_FOUND);
      }

      currentUser.setPinCode(user.getPinCode());
      currentUser.setBirthDate(user.getBirthDate());

      userDetailsService.updateUser(currentUser);
      return new ResponseEntity<User>(currentUser, HttpStatus.OK);
  }
  

  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
	  if(id =="" || id ==null){
  		return new ResponseEntity(new CustomErrorType("User Id can not be blank or nulls "),HttpStatus.NO_CONTENT);
  		
  	}
    User user = userDetailsService.findById(id);
    if (user == null) {
        return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found or deactive."),
                HttpStatus.NOT_FOUND);
    }
   
    user.setActive(false);
    userDetailsService.updateUser(user);
    return new ResponseEntity<User>(HttpStatus.OK);
}
  
  @RequestMapping(value = "/userDetails/", method = RequestMethod.GET)
  public ResponseEntity<List<User>> listAllUsers() {
    List<User> users = userDetailsService.findAllUsers();
    if (users.isEmpty()) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
}

	
}
