package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.database.Queries;
import utils.helpers.PostgisVersion;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Hello mobile HCI experts"));
	}

	public static Result postgis_version() {

		Queries q = new Queries();
		PostgisVersion postgis = q.postgis_version();

		if (postgis != null)
			return ok(Json.toJson(postgis));

		return status(500);

	}

	public static Result register() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		int status = q.register(df.get("email"), df.get("username"),
				df.get("gcmid"));

		return ok("{ \"status\" : " + Json.toJson(status) + "}");

	}

}
