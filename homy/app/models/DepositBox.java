package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class DepositBox extends Model {

	public Float currentDeposit;
	public String name;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	public List<DepositBoxItem> depositBoxItemsList;

	@OneToOne
	public Room owner;

	public DepositBox(String name) {
		this.name = name;
		this.currentDeposit = 0F;
		this.depositBoxItemsList = new LinkedList<DepositBoxItem>();
	}

	public void addBoxItem(DepositBoxItem di) {
		di.save();
		// no lazy update
		currentDeposit = di.income ? di.amount : (-1*di.amount);
		this.depositBoxItemsList.add(di);
		this.save();
	}
}
