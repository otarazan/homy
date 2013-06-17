package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {


    public static void index(){
    	models.Room r = new Room("asd","asda");
    	//List<DepositBoxItem>asd=DepositBoxItem.findAll();
    	r.save();
        render();
    }

}    