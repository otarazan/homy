package models;

import javax.persistence.Entity;
import play.*;
import play.db.jpa.*;

@Entity
public class Task extends Model {
    public String title;
    public boolean done;
    public String assignee;
    public String recurrence;
    public String remainingDate;
    
    public Task(String title){
        this.title=title;
    }
}