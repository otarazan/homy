package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.jpa.Model;


@Entity
public class NotificationMessage extends Model {

	public String text;
	public String target;
	
	public enum ActionCode {
	    ADD, DELETE, EDIT, LOGIN, LOGOFF, LEFTROOM, JOINROOM 
	}
	public ActionCode actionCode;
	
	@ManyToOne
	public Roomy notifee;
	
	@ManyToOne
	public Notifications owner;
	

	public NotificationMessage(String t, ActionCode action,String target){
		this.text = t;
		this.actionCode = action;
		this.target = target;
	}
}
