package entity;

public class Likes {
	private int id;
	private int userId;
	private int postOrCommentId;
	private String postOrComment;
	private String dateLike;
	
	public Likes() {}; //end default constructor 
	
	public Likes(int id, int userId, int postOrCommentId, String postOrComment, String dateLike) {
		this.setId(id); 
		this.setUserId(userId); 
		this.setUserId(userId);
		this.setPostOrCommentId(postOrCommentId);
		this.setPostOrComment(postOrComment);
		this.setDateLike(dateLike);
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

}//end Likes