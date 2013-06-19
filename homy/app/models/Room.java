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
	
//	@OneToOne(mappedBy = "owner")
//	public TaskTable taskTable;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<Roomy> roomysList;

	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		GroceryList gl = new GroceryList("testList");
		gl.save();
		this.groceryList = gl;
		DepositBox db = new DepositBox("testBox");
		db.save();
		this.depositBox = db;
//		TaskTable tt = new TaskTable("testTable");
//		tt.save();
//		this.taskTable = tt;
	}
}
