package controllers;

import java.util.Date;
import java.util.List;

import models.Roomy;
import play.*;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Profile extends Controller{
	
    public static void index(long roomId){
    	
    	String email = Security.connected();
    	Roomy r = Roomy.find("byEmail", email).first();
        render(r,roomId);
    }
    
    public static void updateRoomy(long roomId ,String username, String password, String fname, String lname, String squestion, String sanswer, String bday, String email){
    	Roomy r = Roomy.find("byEmail", email).first();
    	r.birthday = new Date(bday);
    	r.firstName = fname;
    	r.lastName =lname;
    	r.password = password;
    	r.secretQuestion = squestion;
    	r.sqAnswer = sanswer;
    	r.username = username;
    	r.save();
    	index(roomId);
    }
}

