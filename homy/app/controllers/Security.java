package controllers;

import java.util.Date;
import java.util.List;

import play.*;
import play.mvc.*;

import models.Roomy;

public class Security extends Secure.Security{

	static boolean authenticate(String username, String password) {
		Roomy roomy = Roomy.find("byEmail", username).first();
        return roomy != null && roomy.password.equals(password);
    }
	
}


