package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Task extends Controller {


    public static void index(long roomId){

    	String username = Security.connected();
    	Roomy roomy = Roomy.find("byEmail", username).first();
    	List allTasks= models.Task.find("status=false").fetch();
        List allRoomies=Roomy.findAll();
        TaskTable taskTable= (TaskTable) models.TaskTable.findAll().get(0);
        List myTasks=allTasks;//models.Task.find("status=false and roomy=?", roomy).fetch();
        render(allRoomies,myTasks,allTasks,roomId,username);
    }

    public static void changeStatus(long id, boolean status) {
    	models.Task task = models.Task.findById(id);
        task.status=status;
        task.save();
    }
    
    public static void addTask(String task,int recurrence,Long selectedUser,String remainingDate){
    	String mail = Security.connected();
    	Roomy user = (Roomy) Roomy.findAll().get(0);
    	long roomId = user.owner.getId();
    	Roomy roomy=Roomy.findById(selectedUser);
    	TaskTable tasktable=roomy.owner.taskTable;
    	models.Task t=new models.Task(task, recurrence,roomy,remainingDate);
    	tasktable.addTaskItem(t);
    	index(roomId);
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
		task.roomy=unluckyRoomy.firstName;
        task.save();
        renderJSON(task);
    }
    
    
    public static void delete(long id) {
    	models.Task task = models.Task.findById(id);
        task.delete();
        renderJSON(task);
    }
}    