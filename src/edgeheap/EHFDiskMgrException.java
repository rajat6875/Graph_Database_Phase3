/* File hferr.java  */

package edgeheap;
import chainexception.*;

public class EHFDiskMgrException extends ChainException{


  public EHFDiskMgrException()
  {
     super();
  
  }

  public EHFDiskMgrException(Exception ex, String name)
  {
    super(ex, name);
  }



}
