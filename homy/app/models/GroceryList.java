package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class GroceryList extends Model {
	
	@Id
	public String id;
	public LinkedList <GroceryItem> groceryItemsList;

	public GroceryList() {
		super();
		
		// we have to check if play provides an auto-increment function
		//
		// Then check it.
		// See JPA presistence Guide and annotations (@Id)
		//
		this.id = "tobechanged";
		this.groceryItemsList = new LinkedList<GroceryItem>();
	}
    
	
}
