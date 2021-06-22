package UserDetils.model;

public class User {
	
	private String id;
	
    private String fname;
    
    private String lname;
    
    private String email;
    
    private int pinCode;
    
    private String birthDate;
    
    private boolean isActive;
     
	
 
    public User(){
        id="";
    }
     

    public User(String id, String fname,String lname, String email, int pinCode, String birthDate, boolean isActive ){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email=email;
        this.pinCode = pinCode;
        this.birthDate=birthDate;
        this.isActive=isActive;
    }
     

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPinCode() {
		return pinCode;
	}


	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
 
    
}
	

