package controllers;

import play.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;
import utils.database.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Hello mobile HCI experts"));
	}
	
	public static Result postgis_version(){
		
		Queries q = new Queries();
		String postgis = q.postgis_version();

		Postgis_version v = new Postgis_version(postgis);
		
		return ok(Json.toJson(v));
		
	}
	
	private static class Postgis_version{
		
		public String version = "";
		
		public Postgis_version(String v){
			this.version = v;
		}
		
	}

}
