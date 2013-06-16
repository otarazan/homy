package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DepositBoxItem extends Model {
	
	@Id
    public String boxItem_id;
    public String description;
    public Float amount;
    public boolean income;
	
    
    public DepositBoxItem(String description, Float amount,
			boolean income) {
		super();
		this.description = description;
		this.amount = amount;
		this.income = income;
	}
    
    
}
