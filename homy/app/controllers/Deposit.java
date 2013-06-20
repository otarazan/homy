package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Deposit extends Controller {
	

    public static void index(long roomId){
    	String username = Security.connected();
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	List<DepositBoxItem> depositItemList=new LinkedList<DepositBoxItem>();
    	depositItemList=models.DepositBoxItem.find("roomId=? order by id desc",roomId).fetch();
    	float amount=currentRoom.depositBox.currentDeposit;
        render(depositItemList,amount, username, roomId);

    }
    
    public static void addItem(long roomId ,String User,String description,float amount,String income,String expense){
    	Room currentRoom=Room.findById(roomId);
    	DepositBoxItem temp=null;
    	if(income!=null){
    		temp=new DepositBoxItem(description,amount,true);
    	}
    	else if(expense!=null){
    		temp=new DepositBoxItem(description,amount,false);
    	}
    	temp.roomId=1;
    	temp.user=User;
    	currentRoom.depositBox.addBoxItem(temp);
    	index(roomId);
    }
    
    public static void deleteItem(long roomId, long itemId){
    	Room currentRoom = Room.findById(roomId);
    	currentRoom.depositBox.deleteBoxItem(itemId);
    	index(roomId);
    }
    

}    