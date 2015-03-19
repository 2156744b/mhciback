package controllers;

import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.database.Queries;
import utils.helpers.AddFriendResponse;
import utils.helpers.FriendEventResponse;
import utils.helpers.NearbyPublicEventsResponse;
import utils.helpers.PostgisVersion;
import utils.helpers.PublicEventResponse;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Hello mobile HCI experts"));
	}

	public static Result postgisVersion() {

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

		JsonNode json = Json.toJson(status);
		Logger.info(json.toString());
		return ok("{ \"rstatus\" : " + json + "}");

	}

	public static Result addFriend() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		AddFriendResponse response = q.addFriend(df.get("email"));

		JsonNode json = Json.toJson(response);
		Logger.info(json.toString());
		return ok(json);

	}

	public static Result nearbyPublicEvents() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		NearbyPublicEventsResponse response = q.getNearbyPublicEvents(
				df.get("lat"), df.get("lon"), df.get("radius"));

		JsonNode json = Json.toJson(response);
		Logger.info(json.toString());
		return ok(json);

	}

	public static Result getPublicEvent() {
		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		PublicEventResponse response = q.getPublicEvent(df.get("id"));

		JsonNode json = Json.toJson(response);
		Logger.info(json.toString());
		return ok(json);

	}

	public static Result createFriendEvent() {

		DynamicForm df = Form.form().bindFromRequest();

		Queries q = new Queries();
		FriendEventResponse response = q.createFriendEvent(df.get("creator"),
				df.get("lon"), df.get("lat"), df.get("timestamp"),
				df.get("locdescription"), df.get("description"),
				df.get("friends"));

		JsonNode json = Json.toJson(response);

		return ok(json);
	}
}
