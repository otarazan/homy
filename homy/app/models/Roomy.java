package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Roomy extends Model {

	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String email;
	public String secretQuestion;
	public String sqAnswer;
	public Date birthday;

	@ManyToOne
	public Room owner;

	public Roomy(String password, String username, String secretQuestion,
			String firstName, String email, String sqAnswer, String lastName) {
		this.password = password;
		this.username = username;
		this.secretQuestion = secretQuestion;
		this.firstName = firstName;
		this.email = email;
		this.sqAnswer = sqAnswer;
		this.lastName = lastName;
		this.birthday = new Date();
	}
}
