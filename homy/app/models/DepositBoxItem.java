package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DepositBoxItem extends Model {
    public String id;
    public String description;
    public Float amount;
    public boolean income;
	
    
    public DepositBoxItem(String id, String description, Float amount,
			boolean income) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.income = income;
	}
    
    
}
