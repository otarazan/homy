package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task extends Model {
    public String id;
    public String description;
    public boolean status;
    
    /*better declare it as int. we can decide for predefined values,
      like 1=hourly 2=daily 3=weekly etc..*/
    public int recurrence;
    /*better store it as a Date and then calculate remaining time*/
    public Date remainingTime;
	
    
    public Task(String id, String description, boolean status, int recurrence,
			Date remainingTime) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.recurrence = recurrence;
		this.remainingTime = remainingTime;
	}
    
    
    
}
