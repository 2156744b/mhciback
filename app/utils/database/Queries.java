package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import play.Logger;
import utils.gcm.GCMCommunication;
import utils.helpers.AddFriendResponse;
import utils.helpers.FriendEvent;
import utils.helpers.FriendEventResponse;
import utils.helpers.NearbyPublicEventsResponse;
import utils.helpers.PostgisVersion;
import utils.helpers.PublicEvent;
import utils.helpers.PublicEventResponse;

public class Queries {

	public final String CONNECTION_ERROR = "Connection error";

	public PostgisVersion postgis_version() {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {

			String query = "select PostGIS_full_version()";
			st = c.prepareStatement(query);

			Logger.info(st.toString());

			rs = st.executeQuery();

			String result = "";

			while (rs.next())
				result += rs.getString(1);

			return new PostgisVersion(result);

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return null;

	}

	public int register(String email, String username, String gcmid) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return -1;
		}

		try {

			// check if a user exists
			String query = "select count(*) from users where email = ?";
			st = c.prepareStatement(query);
			st.setString(1, email);

			Logger.info(st.toString());

			rs = st.executeQuery();

			int result = -1;

			while (rs.next())
				result = rs.getInt(1);

			rs.close();
			st.close();

			// if not exist, create the user
			if (result == 0) {

				query = "insert into users(email, name, gcmid) values(?,?,?) returning email";
				st = c.prepareStatement(query);
				st.setString(1, email);
				st.setString(2, username);
				st.setString(3, gcmid);

				Logger.info(st.toString());

				rs = st.executeQuery();

				while (rs.next())
					return 200;

			}
			// if exist, update user info
			else {

				query = "update users set name = ?, gcmid = ? where email = ? returning email";
				st = c.prepareStatement(query);
				st.setString(1, username);
				st.setString(2, gcmid);
				st.setString(3, email);

				Logger.info(st.toString());

				rs = st.executeQuery();

				while (rs.next())
					return 200;

			}

			return -1;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return -1;
	}

	public AddFriendResponse addFriend(String email) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		AddFriendResponse response = new AddFriendResponse(-1);

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {

			String query = "select email, name from users where email = ?";
			st = c.prepareStatement(query);
			st.setString(1, email);

			Logger.info(st.toString());

			rs = st.executeQuery();

			while (rs.next())
				response = new AddFriendResponse(200, rs.getString("email"),
						rs.getString("name"));

			return response;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return response;

	}

	public NearbyPublicEventsResponse getNearbyPublicEvents(String lat,
			String lon, String meters) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		NearbyPublicEventsResponse response = new NearbyPublicEventsResponse(-1);

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {

			String query = "select id, type, creator, description, to_char(evdate, 'YYYY-MM-DD HH24:MI') as evdate, poster, url, telephone, description, ST_Y(evlocation) as lat, ST_X(evlocation) as lon, evlocationdescription "
					+ "from publicevents "
					+ "where ST_Transform(evlocation,3786) && ST_Expand(ST_Transform(ST_GeometryFromText(?,4326),3786),?) ";
			st = c.prepareStatement(query);
			st.setString(1, "POINT(" + lon + " " + lat + ")");
			st.setInt(2, Integer.parseInt(meters));

			Logger.info(st.toString());

			rs = st.executeQuery();

			ArrayList<PublicEvent> events = new ArrayList<PublicEvent>();

			while (rs.next()) {

				events.add(new PublicEvent(rs.getInt("id"), rs.getInt("type"),
						rs.getString("creator"), rs.getString("evdate"), rs
								.getString("poster"), rs
								.getString("description"), rs
								.getString("telephone"), rs.getString("lat"),
						rs.getString("lon"), rs
								.getString("evlocationdescription"), rs
								.getString("url")));
			}

			response = new NearbyPublicEventsResponse(200,
					events.toArray(new PublicEvent[events.size()]));

			return response;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return response;
	}

	public PublicEventResponse getPublicEvent(String id) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		PublicEventResponse response = new PublicEventResponse(-1);

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {

			String query = "select id, type, creator, description, to_char(evdate, 'YYYY-MM-DD HH24:MI') as evdate, poster, url, telephone, description, ST_Y(evlocation) as lat, ST_X(evlocation) as lon, evlocationdescription "
					+ "from publicevents " + "where id = ? ";
			st = c.prepareStatement(query);
			st.setString(1, id);

			Logger.info(st.toString());

			rs = st.executeQuery();

			ArrayList<PublicEvent> events = new ArrayList<PublicEvent>();

			while (rs.next()) {

				events.add(new PublicEvent(rs.getInt("id"), rs.getInt("type"),
						rs.getString("creator"), rs.getString("evdate"), rs
								.getString("poster"), rs
								.getString("description"), rs
								.getString("telephone"), rs.getString("lat"),
						rs.getString("lon"), rs
								.getString("evlocationdescription"), rs
								.getString("url")));
			}

			response = new PublicEventResponse(200, events.get(0));

			return response;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return response;
	}

	public FriendEventResponse createFriendEvent(String creator, String lon,
			String lat, String timestamp, String locdescription,
			String description, String friends) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		FriendEventResponse response = new FriendEventResponse(-1);

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {

			String query = "insert into privateevents(creator, description, evdate, evlocation, evlocationdescription) "
					+ "values(?,?,?,ST_GeometryFromText(?,4326),?) returning id";

			st = c.prepareStatement(query);
			st.setString(1, creator);
			st.setString(2, description);
			st.setTimestamp(3, new Timestamp(Long.parseLong(timestamp)));
			st.setString(4, "POINT(" + lon + " " + lat + ")");
			st.setString(5, locdescription);

			Logger.info(st.toString());

			rs = st.executeQuery();

			while (rs.next()) {
				response = new FriendEventResponse(200);
				GCMCommunication gcm = new GCMCommunication();
				gcm.createFriendsEvent(timestamp, locdescription, description,
						lat, lon, getGCMId(friends));
			}

			return response;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return response;
	};

	public String getGCMId(String friends) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		String response = "";

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {
			String[] recipients = friends.split(";");
			String query = "select gcmid from users where ";

			for (int i = 0; i < recipients.length; i++) {
				if (i == 0)
					query += " email = ?";
				else
					query += " or email = ?";
			}

			st = c.prepareStatement(query);

			for (int i = 0; i < recipients.length; i++) {
				st.setString(i + 1, recipients[i]);
			}

			Logger.info(st.toString());

			rs = st.executeQuery();

			while (rs.next()) {
				response += rs.getString("gcmid");
			}

			return response;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return response;
	}

	public FriendEventResponse getFriendEvents(String lat, String lon) {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		FriendEventResponse response = new FriendEventResponse(-1);

		if (c == null) {
			Logger.error(this.getClass().getName() + " connection null");
			return null;
		}

		try {

			String query = "select id, creator, description, to_char(evdate, 'YYYY-MM-DD HH24:MI') as evdate, ST_Y(evlocation) as lat, ST_X(evlocation) as lon, evlocationdescription "
					+ "from privateevents "
					+ "where ST_Transform(evlocation,3786) && ST_Expand(ST_Transform(ST_GeometryFromText(?,4326),3786),?) ";
			st = c.prepareStatement(query);
			st.setString(1, "POINT(" + lon + " " + lat + ")");

			Logger.info(st.toString());

			rs = st.executeQuery();

			ArrayList<FriendEvent> events = new ArrayList<FriendEvent>();

			while (rs.next()) {

				events.add(new FriendEvent(rs.getInt("id"), rs
						.getString("creator"), rs.getString("description"), rs
						.getString("evdate"), rs.getString("lat"), rs
						.getString("lon"), rs
						.getString("evlocationdescription")));
			}

			response = new FriendEventResponse(200,
					events.toArray(new FriendEvent[events.size()]));

			return response;

		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				Logger.error(this.getClass().getName() + " " + e.toString());
			}
		}

		return response;
	}
}