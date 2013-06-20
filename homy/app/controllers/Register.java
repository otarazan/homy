package controllers;

import models.Roomy;
import play.mvc.Controller;

public class Register extends Controller {

	public static void register(String username, String password, String fname, String lname, String squestion, String sanswer, String bday, String email) {
		Roomy nAllowed = Roomy.find("byEmail", username).first();
		if (nAllowed != null){ 
			flash.error("register error");
			try {
				Secure.login();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		Roomy r = new Roomy(password, username, squestion, fname, email, sanswer, lname);
		r.save();
		Dashboard.index();
    }
}
