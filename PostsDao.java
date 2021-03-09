package dao;


import entity.Posts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

	public class PostsDao {
		private Connection connection;
		Posts post = new Posts(); 
		 
		
		// Query pulls all posts for one single user id
		private final String GET_POSTS_BY_POSTERID_QUERY = "SELECT u.username, p.post_text, p.date_posted, p.date_edited " 
															+ "FROM users u"
															+ "INNER JOIN posts p "
															+ "  ON u.id = p.poster_id"
															+"WHERE poster_id = ? "; 
		//Query pull post by post_id
		private final String GET_POST_BY_POSTID_QUERY = "SELECT * FROM POSTS WHERE ID =?"; 
				
		
		// Create a new post
		private final String CREATE_POSTS_QUERY = "INSERT INTO COMMENTS (POST_ID, POASTER_ID, POST_TEXT, DATE_POSTED, DATE_EDITED) VALUES (?, ?, ?, NOW(), NOW())";
		//Update a post
		private final String UPDATE_POSTS_QUERY_BY_ID = "UPDATE POSTS SET POST_TEXT = ?, DATE_EDITED = NOW() WHERE ID = ?";
		
		// Delete dependencies and delete from comments
		private final String DELETE_POST_QUERY_BY_ID = "DELETE FROM POSTS WHERE ID = ?";
		
		// Get connection for Social Media database and create Comment data access object
		public PostsDao() {
			connection = DBConnection.getConnection();
		}//constructor
		
		public Posts getPostsByPostId (int post_id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_POST_BY_POSTID_QUERY); 
			ps.setInt(1, post_id);
			ps.executeQuery();
			return post; 	
		}//end getPostByPostID
		
		// Method queries database by posts and users
		public List<Posts> getsPostsByPoster(int posterId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_POSTS_BY_POSTERID_QUERY);
			ps.setInt(1, posterId);
			ResultSet rs = ps.executeQuery();
			
			// List to store query result
			List<Posts> PostsByPoster = new ArrayList<Posts>();
			
			// Loop through all rows and store in Posts list 
			while (rs.next()) {
				PostsByPoster.add(new Posts(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}//end while
			
			return PostsByPoster;
		}//end getPostsByPoster
		
		// Methods creates a new comment in the database
		public void createNewPost(int postId, int posterId, String postText) throws SQLException {	
			
			// Statement to prevent program error out when parent post does not exist
			PreparedStatement ps = connection.prepareStatement(CREATE_POSTS_QUERY);
			ps.setInt(1, postId);
			ps.setInt(2, posterId);
			ps.setString(3, postText);
			ps.executeQuery();
			}//end createNewPost
			
		
		// Methods updates a post in the database by id
		public void updatePost(int postId, String postText) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(UPDATE_POSTS_QUERY_BY_ID);
			ps.setString(1, postText);
			ps.setInt(2, postId);
			ps.executeUpdate();
		}//end updatePost 
		
		// Method deletes a single post by id
		public void deletePostById(int id) throws SQLException {
			// Prepared statement for deletes posts and likes tables dependency
			PreparedStatement ps = connection.prepareStatement(DELETE_POST_QUERY_BY_ID);
			
			// Set parameter and execute query
			ps.setInt(1, id);
			ps.executeUpdate();
		}// end deletePost
	}//end PostsDao