package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class TaskTable extends Model {

	public String name;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<Task> tasksList;

	@OneToOne
	public Room owner;

	public TaskTable(String name) {
		this.name = name;
		this.tasksList = new LinkedList<Task>();
	}

	public void addTaskItem(Task t) {
		t.save();
		tasksList.add(t);
		this.save();
	}
}
