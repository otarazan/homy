package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.NotificationMessage.ActionCode;

@With(Secure.class)
public class Task extends Controller {

	public static void index(long roomId) {

		String username = Security.connected();
		Roomy roomy = Roomy.find("byEmail", username).first();
		Room r = Room.findById(roomId);
		
		
		List<NotificationMessage> genericAc = r.notifications.getCurrentNotifications();
		List<models.Task> allTasks = new LinkedList<models.Task>();
		if (r.taskTable!=null){
			for (models.Task t : r.taskTable.tasks) {
				if (!t.status)
					allTasks.add(t);
			}
		}

		List allRoomies = Roomy.findAll();
		String warning = "";
		List<models.Task> myTasks = new LinkedList<models.Task>();
		if (roomy != null && r.taskTable!=null) {
			for (models.Task t : r.taskTable.tasks) {
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

	public static void passTask(long roomId, long id) {
		models.Task task = models.Task.findById(id);
		List allRoomies = Roomy.findAll();
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(allRoomies.size());
		Roomy unluckyRoomy = (Roomy) allRoomies.get(index);
		task.roomy = unluckyRoomy.firstName;
		task.save();
		index(roomId);
	}

	public static void delete(long id) {
		models.Task task = models.Task.findById(id);
		task.delete();
		renderJSON(task);
	}
}