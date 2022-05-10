package domain;

import java.util.ResourceBundle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movement {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) private int movementNumber;
	private float kop;
//	1:dirua sartu
//  2:apostua egin
//  3:apostua ezabatu
//	4:apostua irabazi
//	5:apostua galdu
	private int Mmota;
	private float currentMoney;
	
	public Movement( float kop, int mota, float cu) {
		this.kop=kop;
		Mmota=mota;
		currentMoney=cu;
	}
	
	public float getDirua() {
		return currentMoney;
	}
	public int getMovementNumber() {
		return movementNumber;
	}

	public float getKop() {
		if(Mmota==2) {
			return -kop;
		}
		return kop;
	}
	public void setKop(float kop) {
		this.kop = kop;
	}
	public int getMmota() {
		return Mmota;
	}
	public void setMmota(int mmota) {
		Mmota = mmota;
	}
	
	public String getMota() {
		switch(this.Mmota) {
			case 1: return ResourceBundle.getBundle("Etiquetas").getString("DepositMoney");
			case 2: return ResourceBundle.getBundle("Etiquetas").getString("Apostatu");
			case 3: return ResourceBundle.getBundle("Etiquetas").getString("BetDeleted");
			case 4: return ResourceBundle.getBundle("Etiquetas").getString("WinBet");
			case 5: return ResourceBundle.getBundle("Etiquetas").getString("LoseBet");
			case 6: return ResourceBundle.getBundle("Etiquetas").getString("DeleteEvent");
			default: return null;
		}
	}
	
	@Override
	public String toString() {
		return ""+kop+getMota();
	}
	
}
