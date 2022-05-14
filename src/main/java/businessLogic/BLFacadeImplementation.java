package businessLogic;
//hola
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Registered;
import domain.User;
import domain.Bet;
import domain.Event;
import domain.Kuotak;
import exceptions.EventFinished;
import exceptions.FeeAlreadyExist;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    /**
     * 
     */
    @WebMethod public User isLogin(String user, String password) {
    	dbManager.open(false);
    	User a = dbManager.isLogin(user, password);
    	dbManager.close();
    	return a;
    }
    
    /**
     * 
     * @param user
     * @return true if the username is already registered.
     */
    @WebMethod public boolean isRegister(String user) {
    	dbManager.open(false);
    	boolean a = dbManager.isRegister(user);
    	dbManager.close();
    	return a;

    }
    
    @WebMethod public void register(Registered user) {
    	dbManager.open(false);
    	dbManager.register(user);
    	dbManager.close();

    }

	@WebMethod
	public void createEvent(String eventName, Date data) {
	 
		dbManager.open(false);
		dbManager.createEvent(eventName, data);
		
	    
//		if(new Date().compareTo(event.getEventDate())>0)
//			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
					

		dbManager.close();
		
		
	}
	
	@WebMethod
	public void depositMoney(Registered user, Float amount) {
		dbManager.open(false);
    	dbManager.depositMoney(user, amount);
    	dbManager.close();
	}

	@WebMethod
	public void makeBet(Registered User, float betValue, Vector<Kuotak> kuotalist) {
		dbManager.open(false);
    	dbManager.makeBet(User,betValue,kuotalist);
    	dbManager.close();
	}
	
	  @WebMethod
	   public Kuotak createFee(Question q, String ema, float fee) throws EventFinished, FeeAlreadyExist{
			dbManager.open(false);
			Kuotak qry=null;
			
		    
			if(new Date().compareTo(q.getEvent().getEventDate())>0)
				throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
					
			
			qry=dbManager.createFee(q,ema,fee);		

			dbManager.close();
			
			return qry;
	  }

	@WebMethod
	public void deleteBet(Registered User, Bet b) {
		dbManager.open(false);
    	dbManager.deleteBet(User,b);
    	dbManager.close();
	}

	@Override
	public void markResult(Kuotak k) {
		dbManager.open(false);
    	dbManager.markResult(k);
    	dbManager.close();
		
	};
	
	@WebMethod
	public void deleteEvent(Event ev) {
		dbManager.open(false);
		dbManager.deleteEvent(ev);
		dbManager.close();
	}
	
	@WebMethod
	public boolean mezuaBidali(String noriIz, String norkIz, String asun, String ed) {
		dbManager.open(false);
		boolean a = dbManager.isRegister(noriIz);
		if(a == false) return false;
		a = dbManager.mezuaBidali(noriIz,norkIz,asun,ed);
		dbManager.close();
		return a;
	}

	@Override
	public boolean duplicate(Event ev, Date d) {
		boolean a;
		dbManager.open(false);
		a = dbManager.duplicate(ev, d);
		dbManager.close();
		return a;
	}

	@Override
	public Registered getUser(String usrname) {
		Registered a;
		dbManager.open(false);
		a = dbManager.getUser(usrname);
		dbManager.close();
		return a;
	}

	@Override
	public void changePass(Registered us, String pass) {
		dbManager.open(false);
		dbManager.changePass(us, pass);
		dbManager.close();
		
	}
}
	

