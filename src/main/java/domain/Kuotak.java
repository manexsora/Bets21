package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Kuotak {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) Integer FeeNumber;
	
	private String pronostico;
	private double value;
	
	@XmlIDREF 
	private Question galdera;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> bets = new Vector<Bet>();
	
	public Vector<Bet> getBets() {
		return bets;
	}

	public void setBets(Vector<Bet> bets) {
		this.bets = bets;
	}

	public Kuotak(String num, double val, Question q) {

		double a = Math.round(val*100);
		this.pronostico=num;
		this.value=a/100;
		this.galdera = q;
	}
	
	public Integer getFeeN() {
		return FeeNumber;
	}
	
	public String getPronostico() {
		return pronostico;
	}
	
	public void setPronostico(String num) {
		this.pronostico = num;
	}

	public void setValue(double val) {

		this.value = val;
	}

	public double getValue() {
		return this.value;
	}

	public Question getQuestion() {
		return galdera;
	}
	public void addBet(Bet b) {
		bets.add(b);
	}
	public void deleteBet(Bet b) {
		bets.remove(b);
		
	}

}

