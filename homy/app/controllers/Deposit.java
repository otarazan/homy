package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {

	
	static long roomID=0;
	static int counter=0;

    public static void index(long roomId){
    	System.out.println(roomId);
    	Room tmp;
    	if(counter==0){
    		tmp=new Room("asd","asdasd");
    		//tmp.id=roomId;
    		counter++;
    		tmp.save();
    		roomID=tmp.id;
    	}
    	else{
    		tmp=Room.findById(roomID);
    	}
    	
    	
    	//This part will be deleted
    	DepositBoxItem di=new DepositBoxItem("test",123f,true);
    	DepositBoxItem di2=new DepositBoxItem("test2",120f,false);
    	tmp.depositBox.addBoxItem(di);
    	tmp.depositBox.addBoxItem(di2);
    	
    	
    	System.out.println("heyo"+tmp.depositBox.depositBoxItemsList.size());
    	System.out.println("heyo"+roomID);
    	
    	List<DepositBoxItem> depositItemList=new LinkedList<DepositBoxItem>();
    	for(int i=0;i<tmp.depositBox.depositBoxItemsList.size();i++){
    		depositItemList.add(tmp.depositBox.depositBoxItemsList.get(i));
    		
    	}
    	//models.Room r = new Room("asd","asda");
    	//List<DepositBoxItem>asd=DepositBoxItem.findAll();
    	//r.save();
		//models.DepositBoxItem i = new models.DepositBoxItem("asd",10f,true);
    	//r.depositBox.addBoxItem(i);
    	tmp.save();
    	String User="Veli";
        render(depositItemList,User);
    }
    public static void deleteItem(long itemId){

    	DepositBoxItem temp=DepositBoxItem.findById(itemId);
    	temp.delete();
    	index(roomID);
    }
    public static void addItem(String User,String description,float amount,String income,String expense){
    	
    	Room temp=Room.findById(roomID);
    	System.out.println("size before add"+temp.depositBox.depositBoxItemsList.size());
    	if(income!=null){
    		temp.depositBox.addBoxItem(new DepositBoxItem(description,amount,true));
    	}
    	else if(expense!=null){
    		temp.depositBox.addBoxItem(new DepositBoxItem(description,amount,false));
    	}
    	System.out.println("size after add"+temp.depositBox.depositBoxItemsList.size());
    	temp.save();
    	index(roomID);
    }

    

}    