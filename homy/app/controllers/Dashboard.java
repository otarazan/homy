package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Dashboard extends Controller {


    public static void index(){
    	String mail = Security.connected();
    	Roomy roomy = Roomy.find("byEmail", mail).first();
    	List<Room> rooms = Room.findAll();
    	long roomId = roomy.owner.getId();
        render(rooms,roomy,roomId);
    }

    

}    