package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Dashboard extends Controller {

	public static void index() {
		String username = Security.connected();
		Roomy roomy = Roomy.find("byEmail", username).first();
		Room currentRoom = roomy.owner;
		List<NotificationMessage> userAc = currentRoom.notifications.lastGenericActivity;
		List<NotificationMessage> genericAc = currentRoom.notifications.lastUserActivity;
		List<Room> rooms = Room.findAll();
		long roomId = roomy.owner.getId();
		render(rooms, roomy, roomId, username,userAc,genericAc);
	}

	
	public static void newRoom(String name, String description){
		Room r = new Room(name, description);
		r.save();
		index();
	}
}