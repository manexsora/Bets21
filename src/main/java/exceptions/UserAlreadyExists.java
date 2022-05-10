package exceptions;
public class UserAlreadyExists extends Exception {
 private static final long serialVersionUID = 1L;
 
 public UserAlreadyExists()
  {
    super();
  }
  /**This exception is triggered if the user already exists when registering
  *@param s String of the exception
  */
  public UserAlreadyExists(String s)
  {
    super(s);
  }
}