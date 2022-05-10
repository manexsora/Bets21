package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlIDREF;

public class Mezua {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) Integer MezuId;
	private String edukia;
	@XmlIDREF
	private Registered nork;
	@XmlIDREF
	private Registered nori;
	
	public Mezua(String ed, Registered nork, Registered nori) {
		this.edukia=ed;
		this.nork=nork;
		this.nori=nori;
	}
	public String getEdukia() {
		return edukia;
	}
	public void setEdukia(String edukia) {
		this.edukia = edukia;
	}
	public Registered getNork() {
		return nork;
	}
	public void setNork(Registered nork) {
		this.nork = nork;
	}
	public Registered getNori() {
		return nori;
	}
	public void setNori(Registered nori) {
		this.nori = nori;
	}

	
}
