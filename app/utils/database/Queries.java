package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import play.Logger;
import utils.helpers.AddFriendResponse;
import utils.helpers.NearbyPublicEventsResponse;
import utils.helpers.PostgisVersion;
import utils.helpers.PublicEvent;

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


			String query = "select id, type, to_char(evdate, 'YYYY-MM-DD HH24:MI'), poster, description, ST_Y(evlocation) as lat, ST_X(evlocation) as lon "
					+ "from publicevents "
					+ "where ST_Transform(evlocation,3786) && ST_Expand(ST_Transform(ST_GeometryFromText(?,4326),3786),?) ";
			st = c.prepareStatement(query);
			st.setString(1, "POINT(" + lon + " " + lat + ")");
			st.setInt(2, Integer.parseInt(meters));

			Logger.info(st.toString());

			rs = st.executeQuery();

			ArrayList<PublicEvent> events = new ArrayList<PublicEvent>();

			while (rs.next())
				events.add(new PublicEvent(rs.getInt("id"), rs.getInt("type"),
						rs.getString("evdate"), rs.getString("poster"), rs
								.getString("description"), rs.getString("lat"),
						rs.getString("lon")));

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
}