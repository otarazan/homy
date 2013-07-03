package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import play.db.jpa.Model;
@Entity
public class Notifications extends Model {

	public static final int MAX_ITEMS = 3;
	
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
		if (lastGenericActivity.size()>=MAX_ITEMS){

//			lastGenericActivity.get(2).notifee.logMessages.remove(activity);
//			lastGenericActivity.get(2).notifee.save();
//			NotificationMessage toDelete = lastGenericActivity.get(2);
//			toDelete.delete();
//			lastGenericActivity.remove(2);
//			this.save();
			lastGenericActivity.add(activity);
			
		} else {
			lastGenericActivity.add(activity);
		}
		activity.owner = this;
		activity.notifee = roomy;
		activity.save();
		activity.notifee.logMessages.add(activity);
		activity.notifee.save();
		this.save();
	}
	
	
}
