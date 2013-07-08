package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.NotificationMessage.ActionCode;

@With(Secure.class)
public class Deposit extends Controller {

	public static void index(long roomId) {
		String username = Security.connected();
		Room currentRoom = Room.findById(roomId);
		List<NotificationMessage> genericAc = currentRoom.notifications.getCurrentNotifications();
		
		List<DepositBoxItem> depositItemList = new LinkedList<DepositBoxItem>();
		depositItemList = currentRoom.depositBox.depositBoxItemsList;
		float amount = currentRoom.depositBox.currentDeposit();
		render(depositItemList, amount, username, roomId,genericAc);

	}

	public static void addItem(long roomId, String User, String description,
			float amount, String income, String expense) {
		Room currentRoom = Room.findById(roomId);
		
		String username = Security.connected();
		Roomy r = Roomy.find("byEmail", username).first();
		Notification.logGenericActivity(currentRoom, r, ActionCode.ADD, "deposit");
		
		DepositBoxItem temp = null;
		if (income != null) {
			temp = new DepositBoxItem(description, amount, true);
		} else if (expense != null) {
			temp = new DepositBoxItem(description, amount, false);
		}
		temp.roomId = 1;
		temp.user = User;
		currentRoom.depositBox.addBoxItem(temp);
		index(roomId);
	}

	public static void deleteItem(long roomId, long itemId) {
		Room currentRoom = Room.findById(roomId);
		
		String username = Security.connected();
		Roomy r = Roomy.find("byEmail", username).first();
		Notification.logGenericActivity(currentRoom, r, ActionCode.DELETE, "deposit");
		
		currentRoom.depositBox.deleteBoxItem(itemId);
		index(roomId);
	}

}