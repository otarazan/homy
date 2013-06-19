package controllers;

import java.util.Date;
import java.util.List;

import models.Roomy;

public class Security extends Secure.Security{

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
		
		String username = params.get("username");
		System.out.println(username);
		
		
			return false;
	}
}


