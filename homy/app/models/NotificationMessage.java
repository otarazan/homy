package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.jpa.Model;


@Entity
public class NotificationMessage extends Model {
	
	public String text;
	
	public enum ActionCode {
	    ADD, DELETE, EDIT, LOGIN, LOGOFF 
	}
	public ActionCode actionCode;
	
	@ManyToOne
	public Roomy notifee;
	
	@ManyToOne
	public Notifications owner;
	

	public NotificationMessage(String t, ActionCode action){
		this.text = t;
		this.actionCode = action;
	}
}
