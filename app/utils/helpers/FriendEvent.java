package utils.helpers;

public class FriendEvent {

	public int id;
	public String creator;
	public String description;
	public String timestamp;
	public String lat;
	public String lon;
	public String locdescription;

	public FriendEvent(int id, String creator, String description,
			String timestamp, String lat, String lon, String locdesc) {
		this.id = id;
		this.creator = creator;
		this.description = description;
		this.timestamp = timestamp;
		this.lat = lat;
		this.lon = lon;
		this.locdescription = locdesc;
	}

}
