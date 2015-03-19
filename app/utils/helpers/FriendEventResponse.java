package utils.helpers;

public class FriendEventResponse {

	public int rstatus;
	public FriendEvent[] event;

	public FriendEventResponse(int status, FriendEvent[] event) {
		this.rstatus = status;
		this.event = event;
	}

	public FriendEventResponse(int status) {
		this.rstatus = status;
	}

}
