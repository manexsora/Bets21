package exceptions;
public class FeeAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public FeeAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public FeeAlreadyExist(String s)
  {
    super(s);
  }
}