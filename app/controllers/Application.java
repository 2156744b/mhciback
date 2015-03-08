package controllers;

import play.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	
    	String dbstatus = "";
    	
    	try {
    		 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
			dbstatus = e.printStackTrace();
		}
 
 
		Connection connection = null;
 
		try {
 
			String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
			String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
			String database = "mhciback";
			String username = "admin8n47slb";
			String password = "ZuULP8RHLy2n";
			
			connection = DriverManager.getConnection(
					"jdbc:postgresql://" + host + ":" + port + "/" + database, username,
					password);
 
		} catch (SQLException e) {
 			dbstatus = e.printStackTrace(); 
		}
 
		if (connection != null) {
			dbstatus = "Connected!";
			connection.close();
		} else {
			dbstatus = "Connection failed";
		}
	}
    	
        return ok(index.render("Hello mobile HCI experts, database status: " + dbstatus));
    }

}
