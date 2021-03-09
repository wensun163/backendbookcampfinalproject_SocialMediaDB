package entity;

public class Likes {
	private int id;
	private int userId;
	private String username; 
	private int post_id; 
	private int comment_id; 
	private int postOrCommentId;
	private String postOrComment;
	private String dateLike;
	private int postLikes;
	private int commentLikes;
	
	public Likes() {}; //end default constructor 
	
	public Likes(int id, int userId, int postOrCommentId, String postOrComment, String dateLike) {
		this.setId(id); 
		this.setUserId(userId); 
		this.setUserId(userId);
		this.setPostOrCommentId(postOrCommentId);
		this.setPostOrComment(postOrComment);
		this.setDateLike(dateLike);
	}//end constructor 

	public Likes(String username, int post_id, int comment_id) {
		this.setUsername(username); 
		this.setPostId(post_id); 
		this.setCommentId(comment_id);
	}//end constructor 

	public int getId() {
		return id;
	}//end getId

	public void setId(int id) {
		this.id = id;
	}//end setId

	public int getUserId() {
		return userId;
	}//end getUserId

	public void setUserId(int userId) {
		this.userId = userId;
	}//end setUserId

	public int getPostOrCommentId() {
		return postOrCommentId;
	}//end getPostOrCommentId

	public void setPostOrCommentId(int postOrCommentId) {
		this.postOrCommentId = postOrCommentId;
	}//end setPostOrCommentId

	public String getPostOrComment() {
		return postOrComment;
	}//end getPostOrComment

	public void setPostOrComment(String postOrComment) {
		this.postOrComment = postOrComment;
	}//end setPostOrComment

	public String getDateLike() {
		return dateLike;
	}//end getDateLike

	public void setDateLike(String dateLike) {
		this.dateLike = dateLike;
	}//end setDateLike

	public String getUsername() {
		return username;
	}//end getUsername

	public void setUsername(String username) {
		this.username = username;
	}//end setUsername

	public int getPostId() {
		return post_id;
	}//end getPostId

	public void setPostId(int post_id) {
		this.post_id = post_id;
	}//end setPostId

	public int getCommentId() {
		return comment_id;
	}//end getCommentId

	public void setCommentId(int commentId) {
		this.comment_id = commentId;
	}//end setCommentId

	public int getPostLikes() {
		
		return postLikes;
	}//end getPostLikes

	public void setPostLikes(int postLikes) {
		this.postLikes = postLikes;
	}//end setPostLikes

	public int getCommentLikes() {
		return commentLikes;
	}//end getCommentLikes

	public void setCommentLikes(int commentLikes) {
		this.commentLikes = commentLikes;
	}//setCommentLikes

}//end Likes