package domain;

import java.io.*;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Kuotak> fees = new Vector<Kuotak>();
	
	@XmlIDREF
	private Kuotak result;
	@XmlIDREF
	private Event event;

	public Question(){
		super();
	}
	
	
	public Question(String query, float betMinimum, Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}

	
	public Kuotak addFee(String ema, float kop) {
		Kuotak k = new Kuotak(ema, kop, this);
		this.fees.add(k);
		return k;
	}
	
	public Kuotak addFee(Kuotak ku) {
		this.fees.add(ku);
		return ku;
	}
	
	public boolean DoesThisFeeExist(String pron) {
		for(Kuotak a:this.fees) {
			if(a.getPronostico().equals(pron)) return true;
		}
		return false;
	}

	public boolean hasSolution() {
		return(result!=null);
	}


	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public Kuotak getResult() {
		return result;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(Kuotak result) {
		this.result = result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}




	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}


	public Vector<Kuotak> getFees() {
		return fees;
	}



	





	
}