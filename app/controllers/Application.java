package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.database.Queries;
import utils.helpers.AddFriendResponse;
import utils.helpers.NearbyPublicEventsResponse;
import utils.helpers.PostgisVersion;
import views.html.index;

import com.google.gson.Gson;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Hello mobile HCI experts"));
	}

	public static Result postgisVersion() {

		Queries q = new Queries();
		PostgisVersion postgis = q.postgis_version();
		Gson gson = new Gson();

		if (postgis != null)
			return ok(gson.toJson(postgis));

		return status(500);

	}

	public static Result register() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		int status = q.register(df.get("email"), df.get("username"),
				df.get("gcmid"));
		Gson gson = new Gson();

		return ok("{ \"rstatus\" : " + gson.toJson(status) + "}");

	}

	public static Result addFriend() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		AddFriendResponse response = q.addFriend(df.get("email"));

		Gson gson = new Gson();

		return ok(gson.toJson(response));

	}

	public static Result nearbyPublicEvents() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		NearbyPublicEventsResponse response = q.getNearbyPublicEvents(
				df.get("lat"), df.get("lon"), df.get("radius"));

		Gson gson = new Gson();

		return ok(gson.toJson(response));


	}

}
