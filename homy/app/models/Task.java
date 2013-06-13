package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task extends Model {
	@Id
    public String id;
    //public String id;
    public String title;
    public String description;
    public boolean status;
    public String assignee;
    
    /*better declare it as int. we can decide for predefined values,
      like 1=hourly 2=daily 3=weekly etc..*/
    public int recurrence;
    /*better store it as a Date and then calculate remaining time*/
    public Date remainingTime;
	
    public Task(String title){
	super();
        this.title=title;
    }
    
    public Task(String description, String title, boolean status, int recurrence,
			Date remainingTime) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.recurrence = recurrence;
		this.remainingTime = remainingTime;
	}
    
    
    
}
