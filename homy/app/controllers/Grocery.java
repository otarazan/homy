package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Grocery extends Controller {
	static int order=0;
	static long itemId;
    public static void index(long roomId){
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	//currentRoom.groceryList.addItem(new GroceryItem("Ekmek",false,false,"http://www.google.com","Ercan"));
    	//List groceries= models.GroceryItem.find("FROM GroceryItem E WHERE Room.name="+currentRoom.name+" AND E.status = 0 order by id desc").fetch();
    	List groceries=models.GroceryItem.find("status=0 and roomId=? order by id desc",roomId).fetch();
    	if (order==1)
    		groceries=models.GroceryItem.find("status=0 and roomId=? order by assignment desc",roomId).fetch();
    	else if(order==2)
    		groceries=models.GroceryItem.find("status=0 and important=1 and roomId=? order by id desc",roomId).fetch();
    	else if(order==3)
    		groceries=models.GroceryItem.find("status=0 and roomId=? order by deadline ",roomId).fetch();
    	
    		
    	List roomys=currentRoom.roomysList;
    	long id=currentRoom.id;
    	//List dates= long GroceryItem.find("FROM GroceryItem.date E WHERE E.status = 0 order by id desc").fetch();
    	
      	render(groceries,roomys,id);
    }
    
    public static void addGrocery(String name,String assignment, String deadline, String userSelection){
    	int dead=Integer.parseInt(deadline);
    	int select=Integer.parseInt(userSelection);
    	if(select==2)
    		dead=dead*24;
    	else if(select==3)
    		dead=dead*24*7;
    	
    	Date today =new Date();
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	models.GroceryItem gro=new models.GroceryItem(name,false,false,"http://www.lidl.de/de/shop@Search?query="+name,assignment,dead,today,currentRoom.id);
    	System.out.println("Deadline :"+ today);
    	
    	currentRoom.groceryList.addItem(gro);
    	Grocery.index(currentRoom.id);
     }
    
    public static void delete(long groId){
    	models.GroceryItem grocery=models.GroceryItem.findById(groId);
    	Room currentRoom = Room.find("byName","DefaultRoom").first();
    	currentRoom.groceryList.deleteGroceryItem(grocery);
        Grocery.index(currentRoom.id); // how can roomId find?
    }
    
    public static void setDone(long itemId){
  	   models.GroceryItem grocery=models.GroceryItem.findById(itemId);
  	   grocery.status=true;
  	   grocery.save();
  	   Room currentRoom = Room.find("byName","DefaultRoom").first();
  	   index(currentRoom.id);
     }
     
     public static void setImportant(long itemId){
     	models.GroceryItem grocery=models.GroceryItem.findById(itemId);
  	   grocery.important=true;
  	   grocery.save();
  	   Room currentRoom = Room.find("byName","DefaultRoom").first();
	   index(currentRoom.id);
  	   
     }	
     
     public static void setNotImportant(long itemId){
       models.GroceryItem grocery=models.GroceryItem.findById(itemId);
       grocery.important=false;
  	   grocery.save();
  	   Room currentRoom = Room.find("byName","DefaultRoom").first();
	   index(currentRoom.id);
     }
     
    
     public static void assign(String newUser, long id ){
    	models.GroceryItem grocery=models.GroceryItem.findById(id);
    	 grocery.assignment=newUser;
    	 System.out.println("USERRRRRRRR :"+newUser+"IDDD :"+id);
    	 grocery.save();
    	 Room currentRoom = Room.find("byName","DefaultRoom").first();
    	 index(currentRoom.id);
     }
     /*
     public static void alreadyBought(long roomId){
    	 List groceries= models.GroceryItem.find("FROM GroceryItem E WHERE E.status = 1 order by id desc").fetch();
         render(groceries);
     }
     
     public static void deleteAlready(long groId){
     	models.GroceryItem grocery=models.GroceryItem.findById(groId);
     	Room currentRoom = Room.find("byName","DefaultRoom").first();
     	currentRoom.groceryList.deleteGroceryItem(grocery);
     	
         Grocery.alreadyBought(currentRoom.id); // how can roomId find?
     }*/
     
     public static void order(int orderId){
    	 order=orderId;
    	 Room currentRoom = Room.find("byName","DefaultRoom").first();
    	 float a=Math.round(2.6);
    	 System.out.println("Sayiiiiiiiiii:"+a);
    	 index(currentRoom.id);
    	 
     }

}    