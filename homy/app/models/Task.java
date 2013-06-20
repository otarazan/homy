package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Task extends Model {

    public String title;
    public String description;
    public boolean status;
    public String assignee;
  
    @ManyToOne
    public Roomy roomy;
    
    /*better declare it as int. we can decide for predefined values,
      like 1=hourly 2=daily 3=weekly etc..*/
    public Integer recurrence;
    /*better store it as a Date and then calculate remaining time*/
    public String remainingDate;
    
    public Task(String task, int recurrence,Roomy roomy,String remainingDate) {
		this.title = task;
		this.recurrence = recurrence;
		this.remainingDate = remainingDate;
		this.roomy = roomy;
    }
    
    public String toString(){
	return title;
    }
}
