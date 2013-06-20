package controllers;

import java.io.File;
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
    
    public static void updateRoomy(long roomId ,String username, String password, String fname, String lname, String squestion, String sanswer, String bday, File photo){
    	String email = Security.connected();
    	Roomy r = Roomy.find("byEmail", email).first();
    	//if (r.password==password){
	    	r.firstName = fname;
	    	r.lastName =lname;
	    	r.password = password;
	    	r.secretQuestion = squestion;
	    	r.sqAnswer = sanswer;
	    	r.username = username;
	    	r.save();
    	//}
    	index(roomId);
    }
    
    
    public static void updatePhoto(long roomId, File photo){
    	String email = Security.connected();
    	Roomy r = Roomy.find("byEmail", email).first();
    	r.pathToPicture = photo;
    	index(roomId);
    }
}

