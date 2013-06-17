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
	
   	@ManyToOne(fetch=FetchType.LAZY)
  	@JoinColumn(name="OWNER_ID")
	private GroceryList owner;

    public GroceryItem(String name, boolean important, boolean status,
			String buyFromLink) {
		super();
		this.name = name;
		this.important = important;
		this.status = status;
		this.buyFromLink = buyFromLink;
	}
    
    
}
