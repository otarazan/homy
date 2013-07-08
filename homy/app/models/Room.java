package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Room extends Model {

	public String name;
	public String description;

	@OneToOne(mappedBy = "owner")
	public GroceryList groceryList;

	@OneToOne(mappedBy = "owner")
	public DepositBox depositBox;

	@OneToOne(mappedBy = "owner")
	public TaskTable taskTable;
	
	@OneToOne(mappedBy = "owner")
	public Notifications notifications;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<Roomy> roomysList;

	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		this.save();
		GroceryList gl = new GroceryList("testList");
		this.groceryList = gl;
		gl.owner=this;
		gl.save();
		
		DepositBox db = new DepositBox("testBox");
		db.owner=this;
		db.save();
		this.depositBox = db;
		
		TaskTable tt = new TaskTable("testTable");
		tt.owner=this;
		tt.save();
		this.taskTable = tt;

		Notifications nf = new Notifications();
		nf.owner=this;
		nf.save();
		this.notifications = nf;

		
		this.save();
	}
}
