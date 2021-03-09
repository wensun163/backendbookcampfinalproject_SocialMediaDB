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
		
		// Query pulls back how many likes on a user's posts or comments
		private final String GET_POST_COMMENTS_TOTAL_BY_USER_QUERY = "SELECT MAX(POST_LIKE_CNT) AS POST_LIKE_CNT, " +
														"MAX(COMMENT_LIKE_COUNT) AS COMMENT_LIKE_COUNT, " +
														"U.USERNAME " +
														"FROM " +
														"( " +
														"SELECT COUNT(*) AS POST_LIKE_CNT, " +
														"0 AS COMMENT_LIKE_COUNT, " +
														"? AS USER_ID " +
														"FROM POST_LIKES " +
														"WHERE USER_ID = ? " +
														"UNION " + 
														"SELECT 0 AS POST_LIKE_CNT, " + 
														"COUNT(*) AS COMMENT_LIKE_COUNT, " + 
														"? AS USER_ID " + 
														"FROM COMMENT_LIKES " + 
														"WHERE USER_ID = ? " + 
														") AS TOTAL_LIKES " + 
														"INNER JOIN USERS AS U " + 
														"	ON U.ID = TOTAL_LIKES.USER_ID ";
		
		// Like a post or comment
		private final String CREATE_POST_LIKE_QUERY = "INSERT INTO POST_LIKES (USER_ID, POST_ID, DATE_LIKED) VALUES (?, ?, NOW())";
		private final String CREATE_COMMENT_LIKE_QUERY = "INSERT INTO COMMENT_LIKES (USER_ID, COMMENT_ID, DATE_LIKED) VALUES (?, ?, NOW())";
		private final String QUERY_CHECK_POST = "SELECT COUNT(*) AS CNT FROM POSTS WHERE ID = ?";
		private final String QUERY_CHECK_COMMENT = "SELECT COUNT(*) AS CNT FROM COMMENTS WHERE ID = ?";
		private final String QUERY_CHECK_USER = "SELECT COUNT(*) AS CNT FROM USERS WHERE ID = ?";
		
		// Remove a like from a post or comment
		private final String DELEATE_POST_LIKE_QUERY = "DELETE FROM POST_LIKES WHERE ID = ?";
		private final String DELETE_COMMENT_LIKE_QUERY = "DELETE FROM COMMENT_LIKES WHERE ID = ?";
		
		// Update the date the post or comment was liked by id
		private final String UPDATE_COMMENT_LIKE_QUERY = "UPDATE COMMENT_LIKES SET DATE_LIKED = STR_TO_DATE (?, '%Y-%m-%d %H:%i') WHERE ID = ?";
		private final String UPDATE_POST_LIKE_QUERY = "UPDATE POST_LIKES SET DATE_LIKED = STR_TO_DATE (?, '%Y-%m-%d %H:%i') WHERE ID = ?";
		
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
		
		// Method queries database by comments, posts, and users
		public Likes getNumberOfPostsCommentLikesByUser(int userId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(GET_POST_COMMENTS_TOTAL_BY_USER_QUERY);
			
			// The same parameter value four times over
			for (int i = 1; i <= 4; i++)
				ps.setInt(i, userId);
			
			ResultSet rs = ps.executeQuery();
			Likes userLikeCnt = null;
			
			// Loop through all rows and store in Comments list 
			while (rs.next()) {
				userLikeCnt = new Likes(rs.getString(3), rs.getInt(1), rs.getInt(2));
			}
			
			// Return comments, posts, usernames list
			return userLikeCnt;
		}	
		
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
		
		
		// Methods creates a new comment in the database
		public void like (int userId, int id, String type) throws SQLException {	
			
			// Check variable to prevent program erroring out when parent does not exist
			int checkPostComment = 0, checkUser = 0;
			ResultSet rs, rs2;
			
			// Statement to prevent program erroring out when parent comment does not exist
			if (type.equals("comment")) {
				PreparedStatement ps = connection.prepareStatement(QUERY_CHECK_COMMENT);
				ps.setInt(1, id);
				rs = ps.executeQuery();
			}
			else {
				// Statement to prevent program erroring out when parent post does not exist
				PreparedStatement ps = connection.prepareStatement(QUERY_CHECK_POST);
				ps.setInt(1, id);
				rs = ps.executeQuery();
			}
		}
		
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
		
		
		// Method to unlike a comment or post
		public void unlike (int likeId, String type) throws SQLException {
			// Prepared statement for deletes comments and likes tables dependency
			PreparedStatement ps = null;
			if (type.equals("comment")) {
				ps = connection.prepareStatement(DELETE_COMMENT_LIKE_QUERY);
			}
			else
				ps = connection.prepareStatement(DELEATE_POST_LIKE_QUERY);
			
			// Set parameter and execute query
			ps.setInt(1, likeId);
			ps.executeUpdate();
		}
		
		// Method updates post or comment liked date
		public void updateLikeDate(int id, String date, String type) throws SQLException
		{
			PreparedStatement ps = null;
			
			// If comment update comment liked date
			if (type.equals("comment")) {
				ps = connection.prepareStatement(UPDATE_COMMENT_LIKE_QUERY);
				ps.setString(1, date);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			// If post update post liked date
			else {
				// Statement to prevent program erroring out when parent post does not exist
				ps = connection.prepareStatement(UPDATE_POST_LIKE_QUERY);
				ps.setString(1, date);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		}
		
	}//end PostsDao

