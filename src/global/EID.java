package global;

//Source:NID
//– Destination:NID
//– Label:attrString
//– Weight:attrInteger

public class EID extends RID {
	
	
	public EID(){
		super();
	}
    
    public EID (PageId pageno, int slotno)
    {
     super(pageno,slotno);
    }
  
  /**
   * make a copy of the given rid
   */
  public void copyEid (EID rid)
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
