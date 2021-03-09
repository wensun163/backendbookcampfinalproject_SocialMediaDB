package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import entity.User;

public class UserDao {
	
	private Connection connection; 
	private final String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id =?"; 
	private final String CREATE_NEW_USER_QUERY = "INSERT INTO users (id, username, email, password) VALUES(?,?,?,?)"; 
	private final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id =?"; 
	
	
	
	public UserDao() {
		connection = DBConnection.getConnection(); 	
	}//constructor 

	public User getUsersById (int id) throws SQLException {
		User user = new User(); 
		PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID_QUERY); 
		ps.setInt(1, id);
		ps.executeQuery();
		return user; 	
	}//end getUserById
	
	public void createNewUser(int id, String username, String email, String password) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_USER_QUERY); 
		ps.setInt(1, id);
		ps.setString(2,username); 
		ps.setString(3, email);
		ps.setString(4, password);
		ps.executeUpdate(); 
	}//end createNewUser

	public void deleteUserById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID_QUERY); 
		ps.setInt(1, id);
		ps.executeUpdate(); 		
	}//end deleteUserById 
		
	
}//end UserDao
