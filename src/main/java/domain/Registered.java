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
	private int amountBet;
	private int amountWin;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Movement> movements = new Vector<Movement>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> apostuak = new Vector<Bet>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mezua> bidaliak = new Vector<Mezua>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mezua> jasoak = new Vector<Mezua>();
	
	public Vector<Mezua> getJasoak() {
		return jasoak;
	}
	public void setJasoak(Vector<Mezua> jasoak) {
		this.jasoak = jasoak;
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Registered> jarraitzaileak = new Vector<Registered>();

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Registered> jarraituak = new Vector<Registered>();
	
	
	public Registered(String usrnm, String emaila,String izena, String password) {
		super(usrnm,emaila,izena,password);
		setDirua(0);
		amountBet=0;
		amountWin=0;
	}
	public Registered() {
		super();
	}
	
	public void setAmountBet(int a) {
		amountBet=a;
	}
	
	public int getAmountBet() {
		return amountBet;
	}
	
	public void setAmountWin(int a) {
		amountWin=a;
	}
	
	public int getAmountWin() {
		return amountWin;
	}
	
	public Vector<Movement> getMovements() {
		return movements;
	}
	
	public Vector<Bet> getBets() {
		return apostuak;
	}
	
	public String getWinRate() {
		if(amountBet==0) {
			return("0");
		}
		return("%"+((float)getAmountWin()/(float)getAmountBet())*100);
	}
	
	public float getWinRateFloat() {
		if(amountBet==0) {
			return(0);
		}
		return((float)getAmountWin()/(float)getAmountBet());
	}
	


	public void setMovements(Vector<Movement> movements) {
		this.movements = movements;
	}
	
	public Movement addMovement(float kop, int mota) {
		Movement mov = new Movement( kop, mota, dirua);
		movements.add(mov);
		return mov;
	}
	
	public String getEmail() {
		return super.getEmail();
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
	public Mezua mezuaIdatzi(Registered nori, String asun, String ed) {
		Mezua mz = new Mezua(nori,this,asun,ed);
		this.addBidalia(mz);
		nori.addJasoa(mz);
		return mz;
		
	}
	public void addBidalia(Mezua mz) {
		this.bidaliak.add(mz);
	}
	public void addJasoa(Mezua mz) {
		this.jasoak.add(mz);
	}
	public void addFollower(Registered us) {
		jarraitzaileak.add(us);
	}
	public void addFollowing(Registered us) {
		jarraituak.add(us);
	}
	public Vector<Registered> getFollower(){
		return jarraitzaileak;
	}
	public Vector<Registered> getFollowing(){
		return jarraituak;
	}
	
}
