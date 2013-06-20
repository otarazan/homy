package controllers;

import java.util.Date;
import java.util.List;

import models.Roomy;
import play.*;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Profile extends Controller{
    public static void index(){
    	List<Roomy> roomys = Roomy.findAll();
        render();
    }
    
public static void deleteRoomy(long roomyId) {
	Roomy roomy = Roomy.findById(roomyId);
	roomy.delete();
	index();
}

public static void addRoomy(String password, String username, String secretQuestion,
		String firstName, String email, String sqAnswer, String lastName) {
	new Roomy (password, username, secretQuestion, firstName, email, sqAnswer, lastName);
	index();
	
}



}

