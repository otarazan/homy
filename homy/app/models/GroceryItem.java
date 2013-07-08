package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class GroceryItem extends Model {

	public String name;
	public boolean important;
	public boolean status;
	public String buyFromLink;
	public String assignment;
	public int deadline;
	public Date date;
	public long roomId;

	@ManyToOne
	public GroceryList owner;

	public GroceryItem(String name, boolean important, boolean status,
			String buyFromLink, String assignment, int deadline, Date date,
			long roomId) {
		super();
		this.name = name;
		this.important = important;
		this.status = status;
		this.buyFromLink = buyFromLink;
		this.assignment = assignment;
		this.deadline = deadline;
		this.date = date;
		this.roomId = roomId;
		// create();
	}

}
