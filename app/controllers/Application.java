package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import database.PSQLConnection;
import java.sql.Connection;

public class Application extends Controller {

	public static Result index() {

		String dbstatus = "";

		PSQLConnection p = new PSQLConnection();
		Connection c = p.connect();

		if (c != null) {
			dbstatus = "Connected!";
			p.disconnect(c);
		} else
			dbstatus = "Not connected";

		return ok(index.render("Hello mobile HCI experts, <br> database status: "
				+ dbstatus));
	}

}
