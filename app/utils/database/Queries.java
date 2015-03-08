package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.Logger;
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

			if (rs.next())
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
}
