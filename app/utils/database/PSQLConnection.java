package utils.database;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class PSQLConnection {

	public Connection connect() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		Connection connection = null;

		String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
		String database = "mhciback";
		String username = "admin8n47slb";
		String password = "ZuULP8RHLy2n";

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://" + host
					+ ":" + port + "/" + database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return connection;

	}

	public void disconnect(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
