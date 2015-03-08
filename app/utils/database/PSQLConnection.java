package utils.database;

import java.sql.DriverManager;
import play.Logger;
import java.sql.Connection;
import java.sql.SQLException;

public class PSQLConnection {

	public Connection connect() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());
			return null;
		}

		Connection connection = null;

		String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
		String database = "mhciback";
		String username = "admin8n47slb";
		String password = "ZuULP8RHLy2n";

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://"
					+ host + ":" + port + "/" + database, username, password);
		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());
			return null;
		}

		return connection;

	}

	public void disconnect(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			Logger.error(this.getClass().getName() + " " + e.toString());
		}
	}

}
