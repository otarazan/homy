package controllers;

import java.io.File;
import java.util.Date;

import models.NotificationMessage;
import models.NotificationMessage.ActionCode;
import models.Room;
import models.Roomy;
import play.mvc.Controller;

public class Notification {

	
	public static void logGenericActivity(Room room,Roomy roomy, ActionCode action, String controller){
		room.notifications.addGenericActivity(
				roomy,
				new NotificationMessage(roomy.username+" did an "+action+" at "+new Date().toString()+" to "+controller,action,controller)
				);
		}

}
