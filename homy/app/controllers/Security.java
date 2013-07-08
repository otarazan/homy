package controllers;

import java.util.Date;
import java.util.List;

import play.*;
import play.mvc.*;

import models.Room;
import models.Roomy;
import models.NotificationMessage.ActionCode;

public class Security extends Secure.Security {

	static boolean authenticate(String username, String password) {
		Roomy roomy = Roomy.find("byEmail", username).first();
		Logger.info("User logged in");
		if (roomy != null && roomy.password.equals(password)) {

			Room currentRoom = roomy.owner;
			
			Roomy r = Roomy.find("byEmail", username).first();
			Notification.logGenericActivity(currentRoom, r, ActionCode.LOGIN, currentRoom.name);

			return true;
		}
		return false;
	}

}
