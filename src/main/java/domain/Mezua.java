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
	private String asuntua;
	

	public Mezua(Registered noriK, Registered norkK, String asun, String ed) {
		this.nori = noriK;
		this.nork = norkK;
		this.asuntua = asun;
		this.edukia = ed;
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
	public String getAsuntua() {
		return asuntua;
	}
	public void setAsuntua(String asuntua) {
		this.asuntua = asuntua;
	}

	
}
