package controllers;

import java.io.File;
import java.util.Date;

import models.NotificationMessage;
import models.Room;
import models.Roomy;
import play.mvc.Controller;

public class Notification extends Controller {

	public static void logUserActivity(Room room,Roomy roomy){
		room.notifications.addUserActivity(
			new NotificationMessage(roomy,"User "+roomy.username+" was active at "+new Date().toString())
			);
	}
	
	public static void logGenericActivity(Room room,Roomy roomy, String action, String activity){
		room.notifications.addGenericActivity(
				new NotificationMessage(roomy,"User "+roomy.username+" did a "+action+" on "+activity)
				);
		}

}
