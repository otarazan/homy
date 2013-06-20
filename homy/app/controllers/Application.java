package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        renderTemplate("Deposit/index.html");
    }
    
    public static void login(){
        render();
    }
    
    public static void logout(){
        renderTemplate("Application/login.html");
    }

    public static void settings(){
        render();
    }


    public static void whoWeAre(){
        renderTemplate("Application/who.html");
    }


    public static void whatWeDo(){
        renderTemplate("Application/what.html");
    }


    public static void reachUs(){
        renderTemplate("Application/reach.html");
    }
    
}