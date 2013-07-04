package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import models.NotificationMessage.ActionCode;

import play.db.jpa.Model;
@Entity
public class Notifications extends Model {

	public static final int MAX_ITEMS = 6;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<NotificationMessage> lastGenericActivity;
	
	@OneToOne
	public Room owner;
	
	public Notifications(){
		lastGenericActivity = new LinkedList<NotificationMessage>();
	}
	
	
	public void addGenericActivity(Roomy roomy,NotificationMessage activity){
		play.Logger.debug("adding generic activity "+activity);
		play.Logger.debug("size of notifications "+lastGenericActivity.size());
		lastGenericActivity.add(activity);	
		activity.owner = this;
		activity.notifee = roomy;
		activity.save();
		activity.notifee.logMessages.add(activity);
		activity.notifee.save();
		this.save();
	}
	
	public List<NotificationMessage> getCurrentNotifications(){
		List<NotificationMessage> result = new LinkedList<NotificationMessage>();
		int tempWho = 0;
		int tempWhat = 0;
		for (int i=lastGenericActivity.size()-1;i>=0 && result.size()<=MAX_ITEMS ;i--){
			if ((lastGenericActivity.get(i).actionCode == ActionCode.ADD ||
				lastGenericActivity.get(i).actionCode == ActionCode.DELETE ||
				lastGenericActivity.get(i).actionCode == ActionCode.EDIT) &&
					tempWhat<MAX_ITEMS/2) {
					tempWhat++;
					result.add(lastGenericActivity.get(i));
			}
			if ((lastGenericActivity.get(i).actionCode == ActionCode.LOGOFF ||
				lastGenericActivity.get(i).actionCode == ActionCode.LOGIN ||
				lastGenericActivity.get(i).actionCode == ActionCode.JOINROOM ||
				lastGenericActivity.get(i).actionCode == ActionCode.LEFTROOM) &&
				tempWho<MAX_ITEMS/2) {
					tempWho++;
					result.add(lastGenericActivity.get(i));
				}
		}
		return result;
	}
}
