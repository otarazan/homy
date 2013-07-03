package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.jpa.Model;


@Entity
public class NotificationMessage extends Model {
	
	public String text;
	
	@ManyToOne
	public Roomy notifee;
	
	@ManyToOne
	public Notifications owner;
	

	public NotificationMessage(Roomy r, String t){
		this.notifee=r;
		this.text = t;
		this.save();
	}
}
