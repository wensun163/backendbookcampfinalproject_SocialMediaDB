package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.CommentsDao;
import dao.LikesDao;
import dao.PostsDao;
import entity.Comments;
import entity.Likes;
import entity.Posts; 

public class Menu1 {
	// Objects are responsible for directly touching the db. 
	private PostsDao postsDao; 
	private CommentsDao commentsDao;
	private LikesDao likesDao;
	private Scanner scanner = new Scanner(System.in);
	
	// These are options that the user can select from the menu
	// Add new options here
	private List<String> options = Arrays.asList(
			"Display All Posts and Comments by User ID",
			"Create a new post", //new add 
			"Create a new comment",
			"Update Post Text by ID", //new add
			"Update Comment Text by ID",
			"Delete Post by ID", //new add 
			"Delete Comment by ID",
			"Show Post and Comment Likes by User ID", //new add 
			"Show number of Post and Comment Likes by User ID",
			"Like a Post or like a Comment",
			"Unlike a Post or like a Comment",
			"Update Date Liked on a Comment or Post"
	);
	
	// Create a data access object to allow getting data from the db via a layer
	public Menu1() {
		postsDao = new PostsDao(); 
		commentsDao = new CommentsDao();
		likesDao = new LikesDao();
	}
	
	
	// Function serves as the main driver of the Menu object
	public void start() throws SQLException {
		String selection = "";
		
		do {
			
			// Print menu and receive user's menu selection
			printMenu();
			selection = scanner.nextLine();
			
			// These are variables are initialized the by scanner objects and passed to dao objects
			int userId;
			int postId = 0;
			int postCommentId;
			
			// More like, post, and comment variabel
			int commentId;
			int likeId;
			String postText = ""; 
			String commentText;
			String commOrPost;
			String dateLiked;
			
			// Determine which operation
			try {
					switch (selection) {
						// Display all comments and post by the passed in user from the db
						case "1":		
							System.out.println("\nEnter in the ID of the User: ");
							userId = Integer.parseInt(scanner.nextLine());
							System.out.println();
							displayCommentsPostsByUser(userId);//need to write this method 
							System.out.println("\n");
							break;
							
						//Menu option creates a new post 
						case "2": 
							System.out.println("\nEnter the Post ID, Poster ID, and Post Text: ");
							postId = Integer.parseInt(scanner.nextLine());
							userId = Integer.parseInt(scanner.nextLine());
							postText = scanner.nextLine();
							createNewPost(postId, userId, postText);// need to write method at the bottom 
							break;
						
						// Menu option creates a new comment on a post
						case "3":
							System.out.println("\nEnter the Post ID, Commenter ID, and Comment Text: ");
							postId = Integer.parseInt(scanner.nextLine());
							userId = Integer.parseInt(scanner.nextLine());
							commentText = scanner.nextLine();
							createNewComment(postId, userId, commentText);
							break;
						
						
						// Menu option updates a post's text based off what ID and string the user passes in
						case "4":
							System.out.println("\nEnter the Post ID, and Post Text: ");
							commentId = Integer.parseInt(scanner.nextLine());
							commentText = scanner.nextLine();
							updatePostByID(postId, postText);
							break;
							
						// Menu option updates a comment's text based off what ID and string the user passes in
						case "5":
							System.out.println("\nEnter the Comment ID, and Comment Text: ");
							commentId = Integer.parseInt(scanner.nextLine());
							commentText = scanner.nextLine();
							updateCommentByID(commentId, commentText);
							break;
						
						// Menu option deletes a post by ID 
						case "6":
							System.out.println("\nEnter the Post ID to delete a post:  ");
							postId = Integer.parseInt(scanner.nextLine());
							deletePostByID(postId);
							break;
							
						// Menu option deletes a comment by ID 
						case "7":
							System.out.println("\nEnter the Comment ID to delete a comment (will also delete from Likes table due to dependency): ");
							commentId = Integer.parseInt(scanner.nextLine());
							deleteCommentByID(commentId);
							break;
						
						// Show Post and Comment Likes by User ID
						case "8":	
							System.out.println("\nEnter the ID of the User: ");
							userId = Integer.parseInt(scanner.nextLine());
							System.out.println();
							displayCommentsPostsByUser(userId);
							break;
						
						// Show number of likes for one user's posts or comments
						case "9":
							System.out.println("\nEnter the ID of the User: ");
							userId = Integer.parseInt(scanner.nextLine());
							System.out.println();
							displayNumberOfPostsCommentLikesByUser(userId);
							break;
							
						case "10":
							// Ask user if they want to like a post or comment
							System.out.println("\nLike a post or comment?: ");
							commOrPost = scanner.nextLine();
							
							// Checks whether option given was correct or not,
							if (commOrPost.toLowerCase().equals("post") || commOrPost.toLowerCase().equals("comment")) {
								System.out.println("\nGive the ID of the " + commOrPost.toLowerCase() + " and ID of the User: ");
								postCommentId = Integer.parseInt(scanner.nextLine());
								
								// Likes a post or comment
								userId = Integer.parseInt(scanner.nextLine());
								System.out.println();
								likePostOrComment(userId, postCommentId, commOrPost.toLowerCase());			
								
							}
							// Tell user invalid choice
							else
								System.out.println("Invalid option given. Please try again");
							break;
						case "11":
							// Ask user if they want to like a post or comment
							System.out.println("\nUnlike a post or comment?: ");
							commOrPost = scanner.nextLine();
							
							// Checks whether option given was correct or not,
							if (commOrPost.toLowerCase().equals("post") || commOrPost.toLowerCase().equals("comment")) {
								System.out.println("\nGive the Like ID of the "  + commOrPost.toLowerCase() + ": ");
								likeId = Integer.parseInt(scanner.nextLine());
								System.out.println();
								unlikePostOrComment(likeId, commOrPost.toLowerCase());			
							}
							// Tell user invalid choice
							else
								System.out.println("\nInvalid option given. Please try again");
							break;
						case "12":
							// Ask user if they want to like a post or comment
							System.out.println("\nChange the Date Liked of a post or comment?: ");
							commOrPost = scanner.nextLine();
							
							// Checks whether option given was correct or not,
							if (commOrPost.toLowerCase().equals("post") || commOrPost.toLowerCase().equals("comment")) {
								System.out.println("\nGive the Like ID of the "  + commOrPost.toLowerCase() + " and enter the date in this format (YYYY-MM-DD HH:mm): ");
								likeId = Integer.parseInt(scanner.nextLine());
								dateLiked = scanner.nextLine();
								System.out.println();
								updateDateLiked(likeId, dateLiked, commOrPost.toLowerCase());
							}
							// Tell user invalid choice
							else
								System.out.println("\nInvalid option given. Please try again");
							break;
				}
			}
			// Show user any SQL errors
			catch (SQLException e) {
				e.printStackTrace();
			}		
			
			// Line for neatness
			System.out.println();
			
		} while (!selection.equals("-1"));
	}//end start 


	// Loops through list of options that output to the user's screen
		private void printMenu() {
			System.out.println("Select an Option (-1 to terminate program):\n---------------------------------------------");
			for (int i = 0; i < options.size(); i++)
				System.out.println((i + 1) + ") " + options.get(i));
			
			System.out.println("\nSelection: ");
		}//printMenu 
	

		
	//Outputs a list of post and comments by a user id and shows are usernames attached to comments and posts
	private void displayPostsByUser(int posterId) throws SQLException {
		List<Posts> posts = postsDao.getsPostsByPoster(posterId);
		
		// Show comment, post info, and usernames attached to both.
		for (Posts post : posts) {
			System.out.println("Post: " + ((Posts) posts).getPostText() + " Poster: " + ((Posts) posts).getPosterUsername());
		}
		
		// If there are zero rows, tell the user nothing is found
		if (posts.size() == 0)
			System.out.println("Results of Posts by User ID query \"" + posterId + "\" not found.");
	}

	
	// Outputs a list of post and comments by a user id and shows are usernames attached to comments and posts
	private void displayCommentsPostsByUser(int userId) throws SQLException {
		List<Comments> comments = commentsDao.getCommentsPostsByUser(userId);
		
		// Show comment, post info, and usernames attached to both.
		for (Comments comment : comments) {
			System.out.println("Post: " + comment.getPostText() + " | Poster: " + comment.getPosterUsername() + " | Comment: " + comment.getCommentText() + " | Commenter: " + comment.getCommenterUsername());
		}
		
		// If there are zero rows, tell the user nothing is found
		if (comments.size() == 0)
			System.out.println("Results of Posts and Comments by User ID query \"" + userId + "\" not found.");
	}
	
	
	private void createNewPost(int postId, int posterId, String postText) throws SQLException
	{
		postsDao.createNewPost(postId, posterId, postText);
	}//end createNewPost
	
	private void createNewComment(int postId, int commenterId, String commentText) throws SQLException
	{
		commentsDao.createNewComment(postId, commenterId, commentText);
	}//end createNewComment
	
	// Updates a single post text by id 
		private void updatePostByID(int postId, String postText) throws SQLException {
			postsDao.updatePost(postId, postText);	
		}//end updatePostByID
	
	// Updates a single comment text by id 
	private void updateCommentByID(int commentId, String commentText) throws SQLException {
		commentsDao.updateComment(commentId, commentText);	
	}
	
	private void deletePostByID(int postId) throws SQLException {
		commentsDao.deleteCommentById(postId);
	}//end deletePostsByID
	
	private void deleteCommentByID(int commentId) throws SQLException {
		commentsDao.deleteCommentById(commentId);
	}
	
	// Outputs a single row representing how many likes on a user's posts or comments
	private void displayNumberOfPostsCommentLikesByUser(int userId) throws SQLException {
		Likes likeNum = likesDao.getNumberOfPostsCommentLikesByUser(userId);
		
		// If there are zero rows, tell the user nothing is found
		if (likeNum.getUsername() == null)
			System.out.println("Results of Posts and Comments by User ID query \"" + userId + "\" not found.");
		else
			// Show comment, post info, and usernames attached to both.
			System.out.println("Username: " + likeNum.getUsername() + " | Number of Post Likes by this User: " + likeNum.getPostLikes() + " | Number of Comment Likes by this User: " + likeNum.getCommentLikes());
	}
	
	
	// Like a post or comment
		private void likePostOrComment(int userId, int id, String type) throws SQLException
		{
			likesDao.like(userId, id, type);
		}
	

	
	// Remove a like from a comment or post
	private void unlikePostOrComment(int id, String type) throws SQLException
	{
		likesDao.unlike(id, type);
	}
	
	// Update a comment or post liked date by id
	private void updateDateLiked(int id, String date, String type) throws SQLException {
		likesDao.updateLikeDate(id, date, type);
	}
	
}