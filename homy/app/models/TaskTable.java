package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class TaskTable extends Model {

	@OneToMany(mappedBy="owner")
    public List <Task> tasksList;

	public TaskTable() {
		super();
		// we have to check if play provides an auto-increment function*/
		//
		// Then check it.
		// See JPA presistence Guide and annotations (@Id)
		//
		this.tasksList = new LinkedList<Task>();
	}
    
    
}
