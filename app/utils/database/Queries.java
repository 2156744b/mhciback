package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.Logger;
import utils.helpers.AddFriendResponse;
import utils.helpers.PostgisVersion;

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
			Logger.error("query " + st.toString());
			rs = st.executeQuery();

			while (rs.next())
				Logger.error("results :" + rs.getString("email")
						+ " " + rs.getString("name"));
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

}