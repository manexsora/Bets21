package businessLogic;

import java.util.Vector;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Registered;
import domain.User;
import domain.Bet;
import domain.Event;
import domain.Kuotak;
import exceptions.EventFinished;
import exceptions.FeeAlreadyExist;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	/**
	 * This method creates an event
	 * 
	 * @param eventname for the new event
	 * @param data for the new event
	 */
	@WebMethod public void createEvent(String eventName, Date data);
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();


	@WebMethod public User isLogin(String text, String string);
	
	@WebMethod public boolean isRegister(String user);
	
	@WebMethod public void register(Registered user);
	
	@WebMethod public void depositMoney(Registered user, Float amount);

	@WebMethod void makeBet(Registered User, float betValue, Vector<Kuotak> kuotalist);
	
	@WebMethod Kuotak createFee(Question q, String ema, float fee) throws EventFinished, FeeAlreadyExist;
	
	@WebMethod void deleteBet(Registered User, Bet b);
	
	@WebMethod void markResult(Kuotak k);
	
	@WebMethod void deleteEvent(Event Event);
	
	@WebMethod boolean mezuaBidali(String noriIz, String norkIz, String asun, String ed);

	@WebMethod boolean duplicate(Event ev, Date d);
	
	@WebMethod Registered getUser(String usrname);
	
	@WebMethod void changePass(Registered us, String pass);
	
}