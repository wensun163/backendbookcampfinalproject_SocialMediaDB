package entity;

public class Comments {
	// Comments Fields
	private int id;
	private int commenterId;
	private String commenterUsername;
	private String commentText;
	private String dateCommented;
	private String dateEdited;
	
	// Post data fields
	private int postId;
	private String postText;
	private String posterUsername;
	
	public Comments() {}; //end default constructor 
	
	// Special constructor to set only Commenter Username, Comment Text, Post Text, Poster Name
	public Comments(String commenterUsername, String commentText, String postText, String posterUsername) { 
		this.setCommenterUsername(commenterUsername); 
		this.setCommentText(commentText); 
		this.setPostText(postText); 
		this.setPosterUsername(posterUsername); 
	}//end constructor 
	
	public Comments(int id, String commenterUsername, String commentText, String postText, String posterUsername) {
		this.setId(id); 
		this.setCommenterId(commenterId); 
		this.setCommenterUsername(commenterUsername); 
		this.setCommentText(commentText); 
		this.setPostText(postText); 
		this.setPosterUsername(posterUsername); 
	}//end constructor 
	
	public Comments(int postId, String postText, int CommenterId, String commentText, String dateCommented ) {

		this.setPostId(postId); 
		this.setPostText(postText); 
		this.setCommenterId(commenterId);
		this.setCommentText(commentText);
		this.setDateCommented(dateCommented); 
	}//end constructor 
	

	private void setId(int id) {
		this.id = id;			
		}//end setId
	
	private void setPostId(int postId) {
		this.postId= postId; 	
	}//end setPostId
		
	private void setCommenterId(int commenterId) {
		this.commenterId = commenterId;
	}//end setCommenterId

	private void setCommenterUsername(String commenterUsername) {
		this.commenterUsername = commenterUsername; 	
	}//end setCommenterUsername 

	private void setCommentText(String commentText) {
		this.commentText = commentText; 		
	}//end setCommentText

	private void setDateCommented(String dateCommented) {
		this.dateCommented = dateCommented;	
	}//end setDateCommented
	
	private void setPosterUsername(String posterUsername) {
		this.posterUsername = posterUsername; 		
	}// end setPosterUsername 

	private void setPostText(String postText) {
		this.postText = postText; 		
	}//end setPostText

	// Getter for Field id
	public int getId() {
		return id;
	}//end getId
	
	// Getter for Field Post ID
	public int getPostId() {
		return postId;
	}//end getPostId
	
	// Getter for Field Commenter ID
	public int getCommenterId() {
		return commenterId;
	}//end getCommenterId
	
	// Getter for Field Comment Text
	public String getCommentText() {
		return commentText;
	}//end getCommentText
	
	// Getter for Field Date Commented
	public String getDateCommented() {
		return dateCommented;
	}//end getDateCommented
	
	// Getter for Field Date Edited
	public String getDateEdited() {
		return dateEdited;
	}//end getDateEdited

	// Getter for Field Commenter Username
	public String getCommenterUsername() {
		return commenterUsername;
	}//end getCommenterUsername

	// Getter for Field Post Text
	public String getPostText() {
		return postText;
	}//end getPostText

	// Getter for Field Poster Username
	public String getPosterUsername() {
		return posterUsername;
	}//end getPosterUsername
	
}//end Comments