package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

import utils.database.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Hello mobile HCI experts"));
	}
	
	public static Result postgis_version(){
		
		Queries q = new Queries();
		String result = q.postgis_version();
		
		return ok(index.render(result));
		
	}

}
