package dataAccess;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Bet;
import domain.Event;
import domain.Kuotak;
import domain.Movement;
import domain.Question;
import domain.Registered;
import domain.User;
import exceptions.FeeAlreadyExist;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess()  {	
		this(false);
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1 );
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2 );
				q3=ev11.addQuestion("¿Quién ganará el partido?",1 );
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2 );
				q5=ev17.addQuestion("¿Quién ganará el partido?",1 );
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2 );
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1 );
				q2=ev1.addQuestion("Who will score first?",2 );
				q3=ev11.addQuestion("Who will win the match?",1 );
				q4=ev11.addQuestion("How many goals will be scored in the match?",2 );
				q5=ev17.addQuestion("Who will win the match?",1 );
				q6=ev17.addQuestion("Will there be goals in the first half?",2 );
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1 );
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2 );
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1 );
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2 );
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1 );
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2 );

			}

			User u1 = new Admin("admin", "anus", "Admin el capo", "admin");
			Registered a = new Registered("a","a","a","a");
			Kuotak f1=q1.addFee("a", 2);
			Kuotak f2=q1.addFee("b",2);
			Kuotak f3=q2.addFee("c",2);
			a.setDirua(69);

			db.persist(u1);
			db.persist(a);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 


			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);	
			db.persist(f1);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @param fee 
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method  creates an event
	 * 
	 * @param eventname for new event
	 * @param data for the new event
	 */
	public void createEvent(String eventname, Date data){
		//System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		//Event ev = db.find(Event.class, event.getEventNumber());

		//if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Event q = new Event(eventname,data);
		//db.persist(q);
		db.persist(q); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();


	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}


	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}
	public User isLogin(String usr, String pass) {

		try{
			User a = db.find(User.class, usr);
			if(a.isCorrectPassWord(pass)){
				return a;
			}
		}
		catch (Exception e) {

		}
		return null;
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean isRegister(String user) {


		User a = db.find(User.class, user);
		if(a==null) {
			return false;
		}
		return true;


	}

	public void register(Registered user) {
		db.getTransaction().begin();
		db.persist(user);
		db.getTransaction().commit();

		System.out.println("Erregistratuta " + user);
	}

	public void depositMoney(Registered user, float amount) {


		//		Query query = db.createQuery("DELETE FROM User WHERE username='"+ user.getUsername() + "'");
		//		db.getTransaction().commit();

		User bo = db.find(User.class, user.getUsername());
		Registered us = (Registered) bo;
		float a = us.getDirua()+amount;
		us.setDirua(a);
		user.setDirua(a);
		user.addMovement( amount, 1);
		Movement b = us.addMovement(amount, 1);
		db.getTransaction().begin();
		db.persist(us);	
		db.getTransaction().commit();


	}

	public void makeBet(Registered user, float betValue, Vector<Kuotak> kuot) {
		User bo = db.find(User.class, user.getUsername());
		Vector<Kuotak> kuotalist = new Vector<Kuotak>(); 
		for(Kuotak kuo:kuot) {
			Kuotak ku = db.find(Kuotak.class, kuo.getFeeN());
			kuotalist.add(ku);
		}
		Registered us = (Registered) bo;
		float a = us.getDirua()-betValue;
		us.setDirua(a);
		user.setDirua(a);
		user.addMovement(betValue, 2);
		us.addMovement(betValue, 2);
 
		user.addBet(betValue,kuot);
		Bet b = us.addBet(betValue, kuotalist);
		for(Kuotak kk:kuot) {
			kk.addBet(b);
		}

		for(Kuotak kk:kuotalist) {
			kk.addBet(b);
		}



		db.getTransaction().begin();
		db.persist(us);
		db.getTransaction().commit();
		user.getBets().get(user.getBets().size()-1).setBetNumber(us.getBets().get(user.getBets().size()-1).getBetNumber());
	}



	public Kuotak createFee(Question q, String ema, float fee) throws FeeAlreadyExist {
		Question question = db.find(Question.class, q.getQuestionNumber());

		if (question.DoesThisFeeExist(ema)) throw new FeeAlreadyExist("This fee already exists");

		db.getTransaction().begin();
		Kuotak k = question.addFee(ema, fee);

		db.persist(question); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return k;

	}

	public void deleteBet(Registered user, Bet b) {
		User u = db.find(User.class, user.getUsername());
		Registered us = (Registered) u;
		Bet be = db.find(Bet.class, b.getBetNumber());
		Vector<Kuotak> kuotalist = new Vector<Kuotak>();
		for(Kuotak k: b.getKuotaList()) {
			Kuotak ku = db.find(Kuotak.class, k.getFeeN());
			kuotalist.add(ku);
		}
		float a = us.getDirua()+b.getApostatutakoDiruKop();
		us.setDirua(a);
		user.setDirua(a);
		user.addMovement(b.getApostatutakoDiruKop(), 3);
		us.addMovement(b.getApostatutakoDiruKop(), 3);
		us.deleteBet(be);
		user.deleteBet(b);
		for(Kuotak ku: kuotalist) {
			ku.deleteBet(be);
		}
		db.getTransaction().begin();
		db.remove(be);
		db.persist(us);
		db.getTransaction().commit();

	}
	//APOSTU ANITZETAN, ASMATUTAKO APOSTURENBAT BADAGO, ASMATU GABEKO BATEKIN BATERA, ASMATU DEN APOSTUA DATUBASEAN GERATUKO DA NULL BALIOEKIN ATRIBUTUETAN(ez dakigu arazorik emango duen ala ez)
	public void markResult(Kuotak k) {
		Kuotak ku = db.find(Kuotak.class, k);
		Question q = db.find(Question.class, k.getQuestion());
		k.getQuestion().setResult(k);
		q.setResult(ku);

		//		irabazleak
		if(ku.getBets()!=null) {
			for(Bet b:k.getBets()) {
				User u = db.find(User.class, b.getUser().getUsername());
				Registered us = (Registered) u;
				Bet be = db.find(Bet.class, b.getBetNumber());	
				if(b.getKuotaList().size()==1) {
					
					Float a = (float) (be.getApostatutakoDiruKop()*ku.getValue())+us.getDirua();
					us.setDirua(a);
					b.getUser().setDirua(a);
					b.getUser().addMovement((float) (be.getApostatutakoDiruKop()*ku.getValue()), 4);
					us.addMovement((float) (be.getApostatutakoDiruKop()*ku.getValue()), 4);
					us.deleteBet(be);
					b.getUser().deleteBet(b);
					ku.deleteBet(be);
					db.getTransaction().begin();
					db.remove(be);
					db.persist(us);
					db.persist(ku);
					db.getTransaction().commit();
				}else {
					if(be.hasResultList()) {
						Float a = (float) (be.getApostatutakoDiruKop()*b.getKuotaTot())+us.getDirua();
						us.setDirua(a);
						b.getUser().setDirua(a);
						b.getUser().addMovement((float) (be.getApostatutakoDiruKop()*b.getKuotaTot()), 4);
						us.addMovement((float) (be.getApostatutakoDiruKop()*b.getKuotaTot()), 4);
						us.deleteBet(be);
						b.getUser().deleteBet(b);
						ku.deleteBet(be);
						db.getTransaction().begin();
						db.remove(be);
						db.persist(us);
						db.persist(ku);
						db.getTransaction().commit();
					}
				}
			}
		}


		//		galtzaileak
		for(Kuotak ke: k.getQuestion().getFees()) {
			if(k.getPronostico()!=ke.getPronostico() && !ke.getBets().isEmpty()) {
				Kuotak ka = db.find(Kuotak.class, ke);
				for(Bet b:ke.getBets()) {
					Registered us = db.find(Registered.class, b.getUser().getUsername());
					Bet be = db.find(Bet.class, b.getBetNumber());	
					b.getUser().addMovement(0, 5);
					Movement mo = us.addMovement(0, 5);
					us.deleteBet(be);
					b.getUser().deleteBet(b);
					ka.deleteBet(be);
					db.getTransaction().begin();
					db.remove(be);
					db.persist(us);
					db.persist(ka);
					db.getTransaction().commit();
				}	
			}

		}
		db.getTransaction().begin();
		db.persist(q);
		db.getTransaction().commit();
	}

	public void deleteEvent(Event ev) {
		Event event = db.find(Event.class, ev.getEventNumber());
		if(!event.getQuestions().isEmpty() )for(Question q: event.getQuestions()) {
			if(!q.getFees().isEmpty()) for(Kuotak k:q.getFees()) {
				Kuotak ka= db.find(Kuotak.class, k.getFeeN());
				if(!k.getBets().isEmpty()) for(Bet b:k.getBets()) {
					Registered us = db.find(Registered.class, b.getUser().getUsername());
					Bet be = db.find(Bet.class, b.getBetNumber());	

					Movement mo = us.addMovement(be.getApostatutakoDiruKop(), 6);
					us.deleteBet(be);
					us.setDirua(us.getDirua() + be.getApostatutakoDiruKop());
					db.getTransaction().begin();
					db.remove(be);
					db.persist(us);
					db.getTransaction().commit();
				}	
			
			db.getTransaction().begin();
			Kuotak kk = db.find(Kuotak.class, k.getFeeN());
			db.remove(kk);
			db.getTransaction().commit();
		}
		db.getTransaction().begin();
		Question qq = db.find(Question.class, q.getQuestionNumber());
		db.remove(qq);
		db.getTransaction().commit();
	}

	db.getTransaction().begin();
	db.remove(event);
	db.getTransaction().commit();
}

	public boolean mezuaBidali(String noriIz, String norkIz, String asun, String ed) {
		Registered nork = db.find(Registered.class, norkIz);
		Registered nori = db.find(Registered.class, noriIz);
		nork.mezuaIdatzi(nori,asun,ed);
		db.getTransaction().begin();
		db.persist(nork);
		db.getTransaction().commit();
		return true;
	}






}