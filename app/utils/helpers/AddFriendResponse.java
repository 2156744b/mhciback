package utils.helpers;

public class AddFriendResponse {
	
	public String username;
	public String email;
	public int rstatus;
	
	public AddFriendResponse(int status){
		this.rstatus = status;
	}
	
	public AddFriendResponse(int status, String email, String username){
		this.rstatus = status;
		this.email = email;
		this.username = username;
	}

}
