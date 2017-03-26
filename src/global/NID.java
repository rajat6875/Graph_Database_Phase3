package global;
import global.*;

/**
 * Created by nikitashanker on 2/25/17.
 */
public class NID extends RID {
	
	public NID(){
		super();
	}
    
    public NID (PageId pageno, int slotno)
    {
     super(pageno,slotno);
    }
  
  /**
   * make a copy of the given rid
   */
  public void copyNid (NID rid)
    {
      super.pageNo = rid.pageNo;
      super.slotNo = rid.slotNo;
    }
  
  /** Write the rid into a byte array at offset
   * @param ary the specified byte array
   * @param offset the offset of byte array to write 
   * @exception java.io.IOException I/O errors
   */ 
  public void writeToByteArray(byte [] ary, int offset)
    throws java.io.IOException
    {
      super.writeToByteArray(ary,offset);
    }
}
