
import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
    
	 public void doJob() {
	        // Check if the database is empty
	        if(Room.count()==0) {
	            Fixtures.loadModels("initial-homy-data.yml");
	            List<Room> rooms = Room.findAll();
	            for (Room r : rooms){
	            	System.out.println(r.id+": "+r.name + " - " + r.description);
		            System.out.println("GroceryList: "+r.groceryList.count()+" -> with items: "+r.groceryList.groceryItemsList.size());
	//	            System.out.println("TaskList: "+r.taskTable.count()+" -> with items: "+r.taskTable.tasksList.size());
		            System.out.println("DepositBox: "+r.depositBox.count()+"("+r.depositBox.currentDeposit+") -> with items: "+r.depositBox.depositBoxItemsList.size());
		            List<Roomy> roomys = r.roomysList;
		            System.out.println("Num of roomys: "+roomys.size()+" -> "+roomys);
		            for (Roomy roomy : roomys){
		            	System.out.println("Name: " + roomy.username+", "+roomy.email);
		            }
	            }
	        }
	    }    
}