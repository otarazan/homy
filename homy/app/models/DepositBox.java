package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class DepositBox extends Model {

	public String name;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<DepositBoxItem> depositBoxItemsList;

	@OneToOne
	public Room owner;

	public DepositBox(String name) {
		this.name = name;
		this.depositBoxItemsList = new LinkedList<DepositBoxItem>();
	}
	
	public float currentDeposit(){
		float val=0;
		for (DepositBoxItem i: this.depositBoxItemsList){
			if (i.income)
				val += i.amount;
			else
				val -= i.amount;
		}
		return val;
	}

	public void addBoxItem(DepositBoxItem di) {
		di.owner = this;
		di.save();
		this.depositBoxItemsList.add(di);
		this.save();
	}
	
	public void deleteBoxItem(long itemId) {
		DepositBoxItem di=DepositBoxItem.findById(itemId);
		di.delete();
		this.depositBoxItemsList.remove(di);
		this.save();
	}
}
