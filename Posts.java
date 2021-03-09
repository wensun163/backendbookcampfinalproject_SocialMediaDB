package entity;

public class Posts {
	private int id;
	private int posterId;
	private String posterUsername; 
	private String postText;
	private String datePosted;
	private String dateEdited;
	
	public Posts() {}// end default constructor 
	
	public Posts(String posterUsername, String postText, String datePosted, String dateEdited) {
		this.setPosterUsername(posterUsername);
		this.setPostText(postText);
		this.setDatePosted(datePosted);
		this.setDateEdited(dateEdited);
		this.setPostText(postText);
	}//end constructor 
	
	public Posts(String posterUsername, String postText, String datePosted) {
		this.setPosterUsername(posterUsername);
		this.setPostText(postText);
		this.setDatePosted(datePosted);
		this.setPostText(postText);
	}//end constructor 
	
	public Posts(int posterId, String postText, String datePosted) {
		this.setPosterId(posterId);
		this.setPostText(postText);
		this.setDatePosted(datePosted);
		this.setPostText(postText);
	}//end constructor 
	

	public int getId() {
		return id;
	}//end getId

	public void setId(int id) {
		this.id = id;
	}//end setId

	public int getPosterId() {
		return posterId;
	}//end getPosterId


	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}//end setPosterId; 

	public String getPosterUsername() {
		return posterUsername;	
	}//end getPosterUsername
	
	public void setPosterUsername(String posterUsername) {
		this.posterUsername = posterUsername;
	}//end setPosterUserName
	
	public String getPostText() {
		return postText;
	}//end getPostText

	public void setPostText(String postText) {
		this.postText = postText;
	}//end setPostText

	public String getDatePosted() {
		return datePosted;
	}//end getDatePosted

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}//end setDatePosted

	public String getDateEdited() {
		return dateEdited;
	}//end getDateEdited

	public void setDateEdited(String dateEdited) {
		this.dateEdited = dateEdited;
	}//end setDateEdited
	
}//end Posts
