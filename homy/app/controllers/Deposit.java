package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {
	
	static long roomID=0;

    public static void index(long roomId){
    	roomID=roomId;
    	System.out.println(roomId+" <- can be used for retrival");
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	currentRoom.depositBox.addBoxItem(new DepositBoxItem("Something to put in the box", 11.0f, true));
    	currentRoom.depositBox.addBoxItem(new DepositBoxItem("Something to put in the box", 12.0f, true));
    	System.out.println("the current deposit is: "+currentRoom.depositBox.currentDeposit);
    	List<DepositBoxItem> depositItemList=currentRoom.depositBox.depositBoxItemsList;
        render(depositItemList);
    }
    public static void addItem(String User,String description,float amount,String income,String expense){
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	DepositBoxItem temp=null;
    	if(income!=null){
    		temp=new DepositBoxItem(description,amount,true);
    	}
    	else if(expense!=null){
    		temp=new DepositBoxItem(description,amount,false);
    	}
    	currentRoom.depositBox.addBoxItem(temp);
    	index(roomID);
    }
    public static void deleteItem(int roomId){
    	
    }

}    