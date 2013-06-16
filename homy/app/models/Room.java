package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Room extends Model {
   @Id
   public String room_id;
   public String name;
   public String description;
   public GroceryList groceryList;
   public DepositBox depositBox;
   public TaskTable taskTable;
   public LinkedList <Roomy> roomysList;
   
   
   public Room(String name, String description) {
	   super();
	   this.name = name;
	   this.description = description;
	   this.groceryList = new GroceryList();
	   this.depositBox = new DepositBox();
	   this.taskTable = new TaskTable();
	   this.roomysList = new LinkedList<Roomy>();
   }
   
   
   
}
