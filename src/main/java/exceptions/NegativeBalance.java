package exceptions;
public class NegativeBalance extends Exception {
 private static final long serialVersionUID = 1L;
 
 public NegativeBalance()
  {
    super();
  }
  /**This exception is triggered if the balance is negative in the user saldo attribute
  *@param s String of the exception
  */
  public NegativeBalance(String s)
  {
    super(s);
  }
}