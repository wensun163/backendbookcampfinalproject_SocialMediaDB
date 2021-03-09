package dao;

import entity.Likes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


	public class LikesDao {
		private Connection connection;
		Likes postLike = new Likes(); 
		Likes commentLike = new Likes(); 
		Likes likes = new Likes(); 
		 
		
		
		// Query pulls all comments and posts for one single user id
		private final String GET_POSTS_COMMENTS_BY_USER_ID_QUERY = "SELECT U.USERNAME, " + 
				 "PL.POST_ID, "+ "CL.COMMENT_ID, "  +
				"FROM USERS U " + 
				"INNER JOIN POST_LIKES PL" + 
				"	ON U.ID = PL.USER_ID " + 
				"INNER JOIN COMMENT_LIKES CL " + 
				"	ON POST_LIKES.USER_ID = CL.USER_ID " + 
				"WHERE USER_ID = ? " + 
				"ORDER BY DATE_LIKED";
		
		//Query pulls all likes for one post by postId
		private final String GET_LIKES_BY_POSTID_QUERY = "SELECT * FROM POST_LIKES WHERE POST_ID =?"; 
		
		//Query pulls all likes for one comment by commentId
		private final String GET_LIKES_BY_COMMENTID_QUERY = "SELECT * FROM COMMENT_LIKES WHERE COMMENT_ID =?"; 
		
		//Query pull a post_like by post_like_id
		private final String GET_POST_LIKE_BY_POSTLIKESID_QUERY = "SELECT * FROM POST_LIKES WHERE ID =?"; 
		
		//Query pull a comment_like by comment_like_id
		private final String GET_COMMENT_LIKE_BY_COMMENTLIKESID_QUERY = "SELECT * FROM COMMENT_LIKES WHERE ID =?"; 
				
		// Create a new post_like
		private final String CREATE_POST_LIKES_QUERY = "INSERT INTO POST_LIKES (ID, POST_ID, USER_ID, DATE_LIKED) VALUES (?, ?, ?, NOW())";
		//Update a post_like
		//private final String UPDATE_POST_LIKES_QUERY_BY_ID = "UPDATE POSTS SET POST_TEXT = ?, DATE_EDITED = NOW() WHERE ID = ?";
		
		//Create a new comment_like 
		private final String CREATE_COMMENT_LIKES_QUERY = "INSERT INTO COMMENT_LIKES (ID, COMMENT_ID, USER_ID, DATE_LIKED) VALUES (?, ?, ?, NOW())";
		//Update a post_like
		//private final String UPDATE_POSTS_LIKES_QUERY_BY_ID = "UPDATE POST_LIKE SET POST_TEXT = ?, DATE_EDITED = NOW() WHERE ID = ?";
		
		//Delete a like  from a post
		private final String DELETE_POST_LIKE_QUERY_BY_ID = "DELETE FROM POST_LIKES WHERE ID = ?";
		
		// Delete a like  from a comment
		private final String DELETE_COMMENT_LIKE_QUERY_BY_ID = "DELETE FROM COMMENT_LIKES WHERE ID = ?";
		
		// Get connection for Social Media database and create Comment data access object
		public LikesDao() {
			connection = DBConnection.getConnection();
		}//constructor
		
		
		// methods	
		
		public Likes getPostLikesByPostLikeId (int id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_POST_LIKE_BY_POSTLIKESID_QUERY); 
			ps.setInt(1, id);
			ps.executeQuery();
			return postLike; 	
		}//end getPostLikesByPostLikeId
		
		public Likes getPostLikesByCommentLikeId (int id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_COMMENT_LIKE_BY_COMMENTLIKESID_QUERY); 
			ps.setInt(1, id);
			ps.executeQuery();
			return postLike; 	
		}//end getPostLikesByCommentLikeId
		
		
		// Method queries database by comments, posts, and users
		public List<Likes> getPostAndCommentsLikesByUser(int userId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_POSTS_COMMENTS_BY_USER_ID_QUERY);
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			
			// List to store query result
			List<Likes> postAndCommentsLikesByUser = new ArrayList<Likes>();
			
			// Loop through all rows and store in Post_likes and Comment_likes list 
			while (rs.next()) {
				postAndCommentsLikesByUser.add(new Likes(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}//end while
			
			// Return comments, posts, usernames list
			return postAndCommentsLikesByUser;
		}//end getPostAndCommentsLikesByUser
		
				
		public Likes getPostLikesByPostId (int post_id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_LIKES_BY_POSTID_QUERY); 
			ps.setInt(1, post_id);
			ps.executeQuery();
			return postLike; 	
		}//end getPostLikesByPostId
		
		public Likes getCommentLikesByCommentId (int comment_id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement( GET_LIKES_BY_COMMENTID_QUERY); 
			ps.setInt(1, comment_id);
			ps.executeQuery();
			return commentLike; 	
		}//end getCommentLikesByCommentLikeId
		
		
		// Methods creates a new like for the post
		public void createNewPostLike(int id, int user_id, int post_id, String date_liked) throws SQLException {	
			
			PreparedStatement ps = connection.prepareStatement(CREATE_POST_LIKES_QUERY);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			ps.setInt(3, post_id);
			ps.setString(4, date_liked);			
			ps.executeQuery();
			}//end createNewPostLike
			
		// Methods creates a new like for the comment
		public void createNewCommentLike(int id, int user_id, int comment_id, String date_liked) throws SQLException {	
			
			PreparedStatement ps = connection.prepareStatement(CREATE_COMMENT_LIKES_QUERY);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			ps.setInt(3, comment_id);
			ps.setString(4, date_liked);			
			ps.executeQuery();
			}//end createNewCommentLike
	
		
		// Method deletes a single like for a post by id
		public void deletePostLikeById(int id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(DELETE_POST_LIKE_QUERY_BY_ID);
			
			ps.setInt(1, id);
			ps.executeUpdate();
		}// end deletePostLikeById
		
		// Method deletes a single like for a comment by id
		public void deleteCommentLikeById(int id) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(DELETE_COMMENT_LIKE_QUERY_BY_ID);
					
			ps.setInt(1, id);
			ps.executeUpdate();
				}// end deleteCommentLikeById
			
	}//end PostsDao

