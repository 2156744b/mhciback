package utils.helpers;

public class AddFriendResponse {
	
	public String username;
	public String email;
	public int status;
	
	public AddFriendResponse(int status){
		this.status = status;
	}
	
	public AddFriendResponse(int status, String email, String username){
		this.status = status;
		this.email = email;
		this.username = username;
	}

}
