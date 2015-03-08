package utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

	public final String CONNECTION_ERROR = "Connection error";

	public String postgis_version() {

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();
		PreparedStatement st = null;
		ResultSet rs = null;

		if (c == null)
			return CONNECTION_ERROR;

		try {

			String query = "select PostGIS_full_version()";
			st = c.prepareStatement(query);

			rs = st.executeQuery();

			String result = "";

			while (rs.next())
				result += rs.getString(0);
			
			return result;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (c != null)
					p.disconnect(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return CONNECTION_ERROR;

	}

}
