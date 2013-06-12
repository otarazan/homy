package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Room extends Model {
   public String id;
   public String name;
   public String description;
   public GroceryList groceryList;
   public DepositBox depositBox;
   public TaskTable taskTable;
   public LinkedList <Roomy> roomysList;
   
   
   public Room(String id, String name, String description) {
	   super();
	   this.id = id;
	   this.name = name;
	   this.description = description;
	   this.groceryList = new GroceryList();
	   this.depositBox = new DepositBox();
	   this.taskTable = new TaskTable();
	   this.roomysList = new LinkedList<Roomy>();
   }
   
   
   
}
