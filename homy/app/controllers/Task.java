package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Task extends Controller {


    public static void index(){
	List allTasks= models.Task.find("status=false").fetch();
        Roomy roomy=Roomy.find("byUsername", "olcay").first();
        List allRoomies=Roomy.findAll();
        List myTasks= models.Task.find("status=false and roomy=?", roomy).fetch();
        render(allRoomies,myTasks,allTasks);
    }

    public static void changeStatus(long id, boolean status) {
	models.Task task = models.Task.findById(id);
        task.status=status;
        task.save();
    }
    
    public static void addTask(String task,int recurrence,Long selectedUser,String remainingDate){
	Roomy roomy=Roomy.findById(selectedUser);
	
	new models.Task(task, recurrence,roomy,remainingDate).save();
    	index();
    }
    
    public static void sortByDate(){
	
    }
    
    public static void sortbyAssignee(){
	
    }
    
    public static void sortbyImportance(){
	
    }
    
    public static void passTask(long id) {
	models.Task task = models.Task.findById(id);
	List allRoomies=Roomy.findAll();
	Random randomGenerator = new Random();
	int index = randomGenerator.nextInt(allRoomies.size());
	Roomy unluckyRoomy=(Roomy) allRoomies.get(index);
	task.roomy=unluckyRoomy;
        task.save();
        renderJSON(task);
    }
    
    
    public static void delete(long id) {
	models.Task task = models.Task.findById(id);
        task.delete();
        renderJSON(task);
    }
}    