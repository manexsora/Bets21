package domain;


import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlSeeAlso({Registered.class,Admin.class})

@Entity
public abstract class User {
	@Id
	private String username;
	private String passWord;
	private String emaila;
	private String izenabizena;

	
	
	public User(String usrnm, String emaila,String izena, String password) {
		username = usrnm;
		this.emaila= emaila;
		this.passWord = password;
		this.izenabizena = izena;

	}


	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return emaila;
	}

	public String getUsername() {
		return username;
	}
	public void setUsrname(String username) {
		this.username = username;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public boolean isCorrectPassWord(String psw) {
		return this.passWord.equals(psw);
	}


	public String getIzenabizena() {
		return izenabizena;
	}


	public void setIzenabizena(String izenabizena) {
		this.izenabizena = izenabizena;
	}
	
}
