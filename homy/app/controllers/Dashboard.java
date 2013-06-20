package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Dashboard extends Controller {


    public static void index(long roomId){
    	String username = Security.connected();
        render(username);
    }
    
    public static void index(){
    	String username = Security.connected();
        render(username);
    }

}    