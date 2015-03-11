package utils.helpers;

public class PublicEvent {

	public int id;
	public int type;
	public String creator;
	public String description;
	public String date;
	public String posterUrl;
	public String phone;
	public String lat;
	public String lon;
	public String locationDescription;
	public String url;

	public PublicEvent(int id, int type, String date, String posterUrl,
			String description, String lat, String lon) {
		this.id = id;
		this.type = type;
		this.date = date;
		this.posterUrl = posterUrl;
		this.description = description;
		this.lat = lat;
		this.lon = lon;
	}

	public PublicEvent(int id, int type, String creator, String date,
			String posterUrl, String description, String phone, String lat,
			String lon, String locationDescription, String url) {
		this.id = id;
		this.type = type;
		this.creator = creator;
		this.date = date;
		this.posterUrl = posterUrl;
		this.description = description;
		this.phone = phone;
		this.lat = lat;
		this.lon = lon;
		this.locationDescription = locationDescription;
		this.url = url;
	}

}
