package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.NotificationMessage.ActionCode;

@With(Secure.class)
public class Task extends Controller {

	/**
	 * Find all tasks, user tasks and render the page
	 * 
	 * @param roomId current room
	 */
	public static void index(long roomId) {

	    	//Get the current user name
		String username = Security.connected();
		//Find the current user
		Roomy roomy = Roomy.find("byEmail", username).first();
		//Find the current room
		Room currentRoom = Room.findById(roomId);
		//Get notifications
		List<NotificationMessage> genericAc = currentRoom.notifications.getCurrentNotifications();
		//Get all the tasks which is not completed yet
		List<models.Task> allTasks = new LinkedList<models.Task>();
		if (currentRoom.taskTable!=null){
			for (models.Task t : currentRoom.taskTable.tasks) {
				if (!t.status)
					allTasks.add(t);
			}
		}
		
		//Find allRoomies to show on the page
		List<Roomy> allRoomies = currentRoom.roomysList;
		String warning = "";
		
		//Get user tasks
		List<models.Task> myTasks = new LinkedList<models.Task>();
		if (roomy != null && currentRoom.taskTable!=null) {
			for (models.Task t : currentRoom.taskTable.tasks) {
				if (!t.status && t.roomy.equals(roomy.firstName)
						&& t.owner.owner.id == roomId) {
					myTasks.add(t);
				}
			}

		} else {
			warning = "user not found";
		}
		render(allRoomies, myTasks, allTasks, roomId, username, warning,genericAc);
	}

	/**
	 * Change status of task.Usually used for setting it as done
	 * 
	 * @param roomId current room
	 * @param id     current task
	 * @param status status of the task
	 */
	public static void changeStatus(long roomId, long id, boolean status) {
		Room currentRoom = Room.findById(roomId);
		
		String username = Security.connected();
		Roomy r = Roomy.find("byEmail", username).first();
		Notification.logGenericActivity(currentRoom, r, ActionCode.EDIT, "tasks");
		
		models.Task task = models.Task.findById(id);
		task.status = status;
		task.save();
		index(roomId);
	}
	
	/**
	 * Create and assign the task
	 * 
	 * @param roomId        current room
	 * @param task          task title
	 * @param recurrence    importance or recurrence of task
	 * @param selectedUser  assignee of the task
	 * @param remainingDate deadline of the task.This is always shown as a remaining date
	 */
	public static void addTask(long roomId, String task, int recurrence,
			Long selectedUser, String remainingDate) {
		Roomy roomy = Roomy.findById(selectedUser);
		Room r = Room.findById(roomId);
		
		Notification.logGenericActivity(r, roomy, ActionCode.ADD, "tasks");
		
		TaskTable tasktable = r.taskTable;
		models.Task t = new models.Task(task, recurrence, roomy, remainingDate);
		tasktable.addTaskItem(t);
		index(roomId);
	}

	/**
	 * Task is assigned to someone else according to task count.
	 * 
	 * @param roomId  current room
	 * @param id      current task
	 */
	public static void passTask(long roomId, long id) {
	    	//Find task to be assigned
		models.Task task = models.Task.findById(id);
		
		//Find the users in currentRoom
		Room currentRoom = Room.findById(roomId);
		List<Roomy> allRoomies = currentRoom.roomysList;
		
		//Set unluckRoomy as first roomy.
		Roomy unluckyRoomy=allRoomies.get(0);
		//Set minTaskCount as firstRoom task count?
		int minTaskCount=unluckyRoomy.owner.taskTable.tasks.size();
		
		//If there is another roomy who has done less task,s/he will be unlucky today.
		for (Roomy roomy : allRoomies) {
		   int taskCount=roomy.owner.taskTable.tasks.size();
		   if(taskCount<minTaskCount){
		       unluckyRoomy=roomy;
		       minTaskCount=taskCount;
		   }
		}
		task.roomy = unluckyRoomy.firstName;
		task.owner.owner.id=unluckyRoomy.id;
		task.save();
		index(roomId);
	}

	/**
	 * Deletes the specified task
	 * 
	 * @param id     current task
	 */
	public static void delete(long id) {
		models.Task task = models.Task.findById(id);
		task.delete();
		renderJSON(task);
	}
}