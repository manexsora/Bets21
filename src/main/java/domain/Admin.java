package domain;


import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Admin extends User{

	public Admin(String usrnm, String emaila,String izena, String password) {
		super(usrnm,emaila,izena,password);
	}
	
}

