package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        Dashboard.index();
    }
    
    public static void login(){
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