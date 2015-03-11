package utils.helpers;

public class NearbyPublicEventsResponse {
	
	public int rstatus;
	public PublicEvent[] events;
	
	public NearbyPublicEventsResponse(int status){
		this.rstatus = status;
	}
	
	public NearbyPublicEventsResponse(int status, PublicEvent[] events){
		this.rstatus = status;
		this.events = events;
	}
	

}
