package utils.helpers;

public class PublicEventResponse {

	public int rstatus;
	public PublicEvent event;

	public PublicEventResponse(int status) {
		this.rstatus = status;
	}

	public PublicEventResponse(int status, PublicEvent event) {
		this.rstatus = status;
		this.event = event;
	}

}
