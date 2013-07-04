package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.NotificationMessage.ActionCode;

@With(Secure.class)
public class Dashboard extends Controller {

	public static void index() {
		String username = Security.connected();
		Roomy roomy = Roomy.find("byEmail", username).first();
		Room currentRoom = roomy.owner;
		
		
		List<NotificationMessage> genericAc = currentRoom.notifications.getCurrentNotifications();
		List<Room> rooms = Room.findAll();
		long roomId = roomy.owner.getId();
		render(rooms, roomy, roomId, username,genericAc);
	}

	
	public static void newRoom(String name, String description){
		Room r = new Room(name, description);
		r.save();
		index();
	}
}