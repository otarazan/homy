package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DepositBoxItem extends Model {

	public String description;
	public Float amount;
	public boolean income;
	public long roomId;
	public String user;

	@ManyToOne
	public DepositBox owner;

	public DepositBoxItem(String description, Float amount, boolean income) {
		this.description = description;
		this.amount = amount;
		this.income = income;
	}

}
