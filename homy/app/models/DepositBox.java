package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DepositBox extends Model {

	@Id
    public String box_id;
    public Float currentDeposit;
    public LinkedList <DepositBoxItem> depositBoxItemsList;
    
    
	public DepositBox() {
		super();
		// we have to check if play provides an auto-increment function
		//
		// Then check it.
		// See JPA presistence Guide and annotations (@Id)
		//
		this.currentDeposit = 0F;
		this.depositBoxItemsList = new LinkedList<DepositBoxItem>();
	}
    
    
}
