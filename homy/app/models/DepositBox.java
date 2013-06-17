package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DepositBox extends Model {

    public Float currentDeposit;

    @OneToMany(mappedBy="owner")
    public List <DepositBoxItem> depositBoxItemsList;


    
    
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

	public void addBoxItem(DepositBoxItem di){
		di.save();
		this.depositBoxItemsList.add(di);
	}
    
    
}
