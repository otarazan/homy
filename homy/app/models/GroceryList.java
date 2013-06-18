package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class GroceryList extends Model {

	public String name;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<GroceryItem> groceryItemsList;

	@OneToOne
	public Room owner;


	public GroceryList(String name) {
		this.name = name;
		this.groceryItemsList = new LinkedList<GroceryItem>();
	}

	public void addItem(GroceryItem item) {
		item.save();
		this.groceryItemsList.add(item);
		this.save();
	}

}
