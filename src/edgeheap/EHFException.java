/* File hferr.java  */

package edgeheap;
import chainexception.*;

public class EHFException extends ChainException{


  public EHFException()
  {
     super();
  
  }

  public EHFException(Exception ex, String name)
  {
    super(ex, name);
  }



}
