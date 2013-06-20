package controllers;

import play.*;
import play.mvc.*;
import java.util.Date;
import java.util.List;

import models.Roomy;
import play.data.validation.Required;

public class Login extends Controller /* extends Secure.Security*/{

	static boolean authenticate(String username, String password) {
        /*Roomy user = Roomy.;
        System.out.println("sdgdfhdfh");	*/
		/*boolean found = false
		List<Roomy> roomysList = Roomy.findAll();
		for(Roomy roomy : roomysList){
			if( roomy.username.equals(username) &&
		}*/
		Roomy roomy = Roomy.find("byUsername", username).first();
        return roomy != null && roomy.password.equals(password);
    }
	
	public static boolean registerRoomy(/*String password, String username, String secretQuestion,
			String firstName, String email, String sqAnswer, String lastName,
			Date birthday*/){
		//System.out.println("register");
		/*if(Roomy.findById(username)==null){
			Roomy newRoomy =  new Roomy(password, username, secretQuestion, firstName, email, sqAnswer, lastName, birthday);
			newRoomy.save();
			return true;
		}
		else*/
		
		//String username = params.get("username");
		//System.out.println(username);
		
		
			return false;
	}
	public static void login(@Required String username, @Required String password) {
		System.out.println("Inside Login handler");///////////
		if (!validation.hasErrors()) {
			System.out.println("Inside Login handler: search username");///////////
			Roomy roomy = Roomy.find("byUsername",username).first();
			
			validation.required(roomy).key("username").message(
			"validation.username_not_found");
			
			if (!validation.hasErrors()) {
				System.out.println("Inside Login handler: check password");///////////
				validation.equals(password, roomy.password).key(
				"password").message("validation.invalid_password");
				
				if (!validation.hasErrors()) {
					System.out.println("Inside Login handler: if all correct");///////////
					session.put("roomy", roomy.username);
					Application.index();
					return;
				}
			}
		}

		params.flash();
		validation.keep();
		Application.login();
	}
	
	public static void register(@Required String username, @Required String email, @Required String password, @Required String firstName,
			@Required String lastName, @Required String birthday, @Required String secQuestion,@Required String sqAnswer ) {
		System.out.println("Inside Register handler");///////////
		//if (!validation.hasErrors()) {
			System.out.println("Inside Register handler: search username");///////////
			Roomy roomy = Roomy.find("byUsername",username).first();
			
			if(roomy==null){
				System.out.println("Inside Register handler: insert user");///////////
				roomy = new Roomy(password,username,secQuestion,
						firstName,email, sqAnswer,lastName);
				session.put("roomy", roomy.username);
				Application.index();
				return;
			}
			else{
				//username already exists
				System.out.println("Username already exists");///////////
				
			}
		//}
		System.out.println("Inside Register handler:errors");///////////
		params.flash();
		validation.keep();
		Application.login();
	}
}


