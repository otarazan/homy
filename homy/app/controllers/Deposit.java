package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {
	
	static long roomID=0;
	static int state=0;

    public static void index(long roomId){
    	System.out.println(roomId+" <- can be used for retrival");
    	roomID=roomId;
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	//currentRoom.depositBox.addBoxItem(new DepositBoxItem("Something to put in the box", 11.0f, true));
    	//currentRoom.depositBox.addBoxItem(new DepositBoxItem("Something to put in the box", 11.0f, true));
    	System.out.println("the current deposit is: "+currentRoom.depositBox.currentDeposit);
    	for (DepositBoxItem item : currentRoom.depositBox.depositBoxItemsList){
    		System.out.println(item.description+" - "+item.amount);
    	}
    	List<DepositBoxItem> depositItemList=new LinkedList<DepositBoxItem>();
    	if(state==0){
    		depositItemList=models.DepositBoxItem.find("roomId=? order by id desc",roomID).fetch();
    	}
    	else if(state==1){
    		depositItemList=models.DepositBoxItem.find("roomId=? order by income desc",roomID).fetch();
    	}
    	else{
    		depositItemList=models.DepositBoxItem.find("roomId=? order by user desc",roomID).fetch();
    	}
    	   
    	float amount=currentRoom.depositBox.currentDeposit;
    	
        render(depositItemList,amount);

    }
    
    public static void addItem(String User,String description,float amount,String income,String expense){
    	//Room currentRoom = Room.find("byName","DefaultRoom").first();
    	Room currentRoom=Room.findById(roomID);
    	DepositBoxItem temp=null;
    	if(income!=null){
    		temp=new DepositBoxItem(description,amount,true);
    	}
    	else if(expense!=null){
    		temp=new DepositBoxItem(description,amount,false);
    	}
    	temp.roomId=roomID;
    	temp.user=User;
    	currentRoom.depositBox.addBoxItem(temp);
    	index(roomID);
    }
    
    public static void deleteItem(long itemId){
    	Room currentRoom = Room.findById(roomID);
    	currentRoom.depositBox.deleteBoxItem(itemId);
    	index(roomID);
    }
    
    public static void sortItemIncome(){
    	state=1;
    	index(roomID);
    }
    public static void sortItemDate(){
    	state=0;
    	index(roomID);
    }
    public static void sortItemAssignee(){
    	state=2;
    	index(roomID);
    }
    

}    