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
    	List<Roomy> roomys = Roomy.findAll();
    	String username = Security.connected();
        render(username);
    }
    
public static void deleteRoomy(long roomyId) {
	Roomy roomy = Roomy.findById(roomyId);
	roomy.delete();
	index(roomyId);
}



}

