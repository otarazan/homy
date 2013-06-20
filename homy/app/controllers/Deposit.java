package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {


    public static void index(int roomId){
    	System.out.println(roomId+" <- can be used for retrival");
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	currentRoom.depositBox.addBoxItem(new DepositBoxItem("Something to put in the box", 11.0f, true));
    	currentRoom.depositBox.addBoxItem(new DepositBoxItem("Something to put in the box", 11.0f, true));
    	System.out.println("the current deposit is: "+currentRoom.depositBox.currentDeposit);
    	for (DepositBoxItem item : currentRoom.depositBox.depositBoxItemsList){
    		System.out.println(item.description+" - "+item.amount);
    	}
        render();
    }

}    