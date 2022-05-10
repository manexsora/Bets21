package exceptions;
public class MinimumBetNotFulfilled extends Exception {
 private static final long serialVersionUID = 1L;
 
 public MinimumBetNotFulfilled()
  {
    super();
  }
  /**This exception is triggered if the bet is not over the minimum bet permitted
  *@param s String of the exception
  */
  public MinimumBetNotFulfilled(String s)
  {
    super(s);
  }
}