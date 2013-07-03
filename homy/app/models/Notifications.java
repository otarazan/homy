package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;
@Entity
public class Notifications extends Model {

	public static final int MAX_ITEMS = 3;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<NotificationMessage> lastGenericActivity;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<NotificationMessage> lastUserActivity;
	
	
	@OneToOne
	public Room owner;
	
	public Notifications(){
		lastUserActivity = new LinkedList<NotificationMessage>();
		lastGenericActivity = new LinkedList<NotificationMessage>();
	}
	
	public void addUserActivity(NotificationMessage userActivity){
		if (lastUserActivity.size()>MAX_ITEMS){
			lastUserActivity.remove(0);
			lastUserActivity.add(0, lastUserActivity.get(1));
			lastUserActivity.add(1, lastUserActivity.get(2));
			lastUserActivity.add(2,userActivity);
		} else {
			lastUserActivity.add(userActivity);
		}
		this.save();
	}
	
	public void addGenericActivity(NotificationMessage activity){
		if (lastGenericActivity.size()>MAX_ITEMS){
			lastGenericActivity.remove(0);
			lastGenericActivity.add(0, lastGenericActivity.get(1));
			lastGenericActivity.add(1, lastGenericActivity.get(2));
			lastGenericActivity.add(2,activity);
		} else {
			lastGenericActivity.add(activity);
		}
		this.save();
	}
	
	
}
