package entity;

public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	
	public User () {}; //end default constructor 
	
	public User (int id, String username, String email, String password) {
		this.setUserId (id); 
		this.setUserName(username); 
		this.setEmail(email); 
		this.setPassword(password); 
		}//end constructor

	public int getUserId() {
		return id;
	}// send getUserId
	
	private void setUserId(int id) {
		this.id = id;
	}//end setUserId
	
	public String getUsername() {
		return username;
	}//end getUsername
	
	private void setUserName(String username) {
		this.username = username;	
	}//end setUsername


	public String getEmail() {
		return email;
	}// end getEmail
	
	private void setEmail(String email) {
		this.email = email; 	
	}//end setEmail 
	
	public String getPassword() {
		return password;
	}//end getPassword

	private void setPassword(String password) {
		this.password = password; 	
	}//end setPassword

}

