package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Dashboard extends Controller {


    public static void index(){
        render();
    }

}    