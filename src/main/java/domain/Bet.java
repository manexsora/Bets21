package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Bet {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) Integer betNumber;
	

	private float apostatutakoDiruKop;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Kuotak> kuotaList;
	
	private float kuotaTot;
	
	@XmlIDREF
	private Registered User;
	

	public Integer getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(int betNumber) {
		this.betNumber = betNumber;
	}

	public Registered getUser() {
		return User;
	}

	public void setUser(Registered user) {
		User = user;
	}

	public Bet (float diruKop, Registered user) {

		apostatutakoDiruKop = diruKop;
		this.User = user;
	}
	
	public Bet (float diruKop, Vector<Kuotak> kuota, Registered user) {

		apostatutakoDiruKop = diruKop;
		this.User = user;
		this.kuotaList=kuota;
		float a=1;
		for(Kuotak k:kuota) {
			a*=k.getValue();
		}
		this.kuotaTot= a;
	}
	public float getApostatutakoDiruKop() {
		return apostatutakoDiruKop;
	}

	public void setApostatutakoDiruKop(float apostatutakoDiruKop) {
		this.apostatutakoDiruKop = apostatutakoDiruKop;
	}

	public Vector<Kuotak> getKuotaList() {
		return this.kuotaList;
	}

	public float getKuotaTot() {
		return kuotaTot;
	}

	public void setKuotaTot(float kuotaTot) {
		this.kuotaTot = kuotaTot;
	}
	
	public boolean hasResultList() {
		for(Kuotak k:this.kuotaList) {
			if(k.getQuestion().getResult()==null) {
				return false;
			}
		}
		return true;
	}
	



}