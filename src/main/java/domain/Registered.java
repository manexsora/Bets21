package domain;


import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Registered extends User{

	private float dirua;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Movement> movements = new Vector<Movement>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> apostuak = new Vector<Bet>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mezua> mezuak = new Vector<Mezua>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Jarraitu> jarraitzaileak = new Vector<Jarraitu>();

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Jarraitu> jarraituak = new Vector<Jarraitu>();
	
	
	public Registered(String usrnm, String emaila,String izena, String password) {
		super(usrnm,emaila,izena,password);
		setDirua(0);
	}
	public Registered() {
		super();
	}
	
	public Vector<Movement> getMovements() {
		return movements;
	}
	
	public Vector<Bet> getBets() {
		return apostuak;
	}
	


	public void setMovements(Vector<Movement> movements) {
		this.movements = movements;
	}
	
	public Movement addMovement(float kop, int mota) {
		Movement mov = new Movement( kop, mota, dirua);
		movements.add(mov);
		return mov;
	}
	
	public float getDirua() {
		return dirua;
	}

	public void setDirua(float dirua) {
		this.dirua = dirua;
	}
	
	public Bet addBet(float diruKop, Vector<Kuotak> kuota) {
		Bet bet = new Bet(diruKop, kuota,this);
		apostuak.add(bet);
		return bet;
	}

	@Override
	public String toString() {
		String b = "";
		for(Movement a : movements) {
			b = b + a.toString()+" \n ";
		}
		return b;
	}
	public void deleteBet(Bet b) {
		apostuak.remove(b);
		
	}
}
