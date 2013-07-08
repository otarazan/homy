package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.NotificationMessage;
import models.Room;
import models.Roomy;
import models.NotificationMessage.ActionCode;
import play.*;
import play.db.jpa.Blob;
import play.libs.Images;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Profile extends Controller {

	public static void index() {
		String username = Security.connected();
		Roomy r = Roomy.find("byEmail", username).first();
		Room currentRoom = r.owner;
		List<NotificationMessage> genericAc = currentRoom.notifications.getCurrentNotifications();
		String rImage="";
		try {
			if (r != null && r.pathToPicture!=null)	{
					rImage = Images.toBase64(new File(r.pathToPicture));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long roomId = r.owner.getId();
		List<Room> rooms = Room.findAll();
		render(roomId, r,rImage, username, rooms,genericAc);
	}

	public static void updateRoomy(String username, String password,
			String fname, String lname, String squestion, String sanswer,
			String bday, File photo) {
		String email = Security.connected();
		Roomy r = Roomy.find("byEmail", email).first();
		if (password.equals(r.password)) {
			r.firstName = fname;
			r.lastName = lname;
			r.password = password;
			r.secretQuestion = squestion;
			r.sqAnswer = sanswer;
			r.username = username;
			r.save();
		}
		index();
	}

	public static void updatePhoto(Blob photo) {
		String email = Security.connected();
		Roomy r = Roomy.find("byEmail", email).first();
		r.pathToPicture = photo.getFile().getAbsolutePath();
		r.save();
		index();
	}

	public static void updateCurrentRoom(long newRoom) {
		String email = Security.connected();
		Roomy r = Roomy.find("byEmail", email).first();
		Notification.logGenericActivity(r.owner, r, ActionCode.LEFTROOM, r.owner.name);
		Room room = Room.findById(newRoom);
		r.owner = room;
		Notification.logGenericActivity(room, r, ActionCode.JOINROOM, room.name);
		r.save();
		room.save();
		Dashboard.index();
	}
}
