package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {


    public static void index(int roomId){
    	System.out.println(roomId);
    	models.Room r = new Room("asd","asda");
    	r.save();
		models.DepositBoxItem i = new models.DepositBoxItem("asd",10f,true);
    	r.depositBox.addBoxItem(i);
        render();
    }

}    