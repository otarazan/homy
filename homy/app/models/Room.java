package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Room extends Model {

   public String name;
   public String description;
   
   @OneToOne(fetch=FetchType.LAZY)
   public GroceryList groceryList;

   @OneToOne(fetch=FetchType.LAZY)
   public DepositBox depositBox;
   
   @OneToOne(fetch=FetchType.LAZY)
   public TaskTable taskTable;

   @OneToMany(mappedBy="owner")
   public List <Roomy> roomysList;
   
   
   public Room(String name, String description) {
	   super();
	   this.name = name;
	   this.description = description;
      GroceryList gl = new GroceryList();
      gl.save();
	   this.groceryList = gl;

      DepositBox db = new DepositBox();
      db.save();
	   this.depositBox = db;

      TaskTable tt = new TaskTable();
      tt.save();
	   this.taskTable = tt;


	   //this.roomysList = new LinkedList<Roomy>();
   }
   
   
   
}
