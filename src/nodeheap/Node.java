/* File Node.java */
package nodeheap;

import java.io.*;
import java.lang.*;
import global.*;
import heap.Tuple;


public class Node extends Tuple implements GlobalConst{


 /** 
  * Maximum size of any tuple
  */
 // public static final int max_size = MINIBASE_PAGESIZE;
  

  /**
   * start position of this tuple in data[]
   */
  //private int node_offset;

  /**
   * length of this tuple
   */
 // private int node_length;

  private String Label;
 // private byte[] lblByte;  

  private Descriptor Desc;

   /**
    * Class constructor
    * Creat a new tuple with length = max_size,tuple offset = 0.
    */

  public  Node()
  {
       // Create a new tuple
	AttrType [] NTypes = {
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
                new AttrType(AttrType.attrString),
	};
	short[] Nsize = new short[1];      
        Nsize[0] = 10;
	try{
		super.setHdr((short) 6, NTypes, Nsize);
	} 
	catch (Exception e){
		e.printStackTrace();	
	}
	//super();
  }
   
   /** Constructor
    * @param atuple a byte array which contains the tuple
    * @param offset the offset of the tuple in the byte array
    * @param length the length of the tuple
    */
   public Node(byte [] anode, int offset)
   {
       // node_length = anode.size();
	AttrType [] NTypes = {
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
                new AttrType(AttrType.attrString),
	};
	short[] Nsize = new short[1];      
        Nsize[0] = 10;
	try{
		super.setHdr((short) 6, NTypes, Nsize);
	} 
	catch (Exception e){
		e.printStackTrace();	
	}
        super.tupleSet(anode,offset,anode.length); 
     //tuple_length = length;
    //  fldCnt = getShortValue(offset, data);
   }
   
   /** Constructor(used as tuple copy)
    * @param fromTuple   a byte array which contains the tuple
    * 
    */
   public Node(Node fromNode)
   {
       super.tupleSet(fromNode.getNodeByteArray(),0,fromNode.getLength());
       //tuple_length = fromTuple.getLength();
       //tuple_offset = 0;
       //fldCnt = fromTuple.noOfFlds(); 
       //fldOffset = fromTuple.copyFldOffset(); 
   }

   
   public void nodeCopy(Node fromNode)
   {
       super.tupleSet(fromNode.getNodeByteArray(),0,fromNode.getLength());		
   }

   /** This is used when you don't want to use the constructor
    * @param atuple  a byte array which contains the tuple
    * @param offset the offset of the tuple in the byte array
    * @param length the length of the tuple
    */

   public void nodeInit(byte [] anode, int offset)
   {
	super.tupleInit(anode ,offset, anode.length);
    //  tuple_length = length;
   }

 /**
  * Set a tuple with the given tuple length and offset
  * @param	record	a byte array contains the tuple
  * @param	offset  the offset of the tuple ( =0 by default)
  * @param	length	the length of the tuple
  */
 public void nodeSet(byte [] fromNode, int offset)  
  {
	super.tupleSet(fromNode,offset,fromNode.length);;
      //tuple_length = length;
  }
  
 /** get the length of a tuple, call this method if you did not 
  *  call setHdr () before
  * @return 	length of this tuple in bytes
  */   
  public int getLength()
   {
      return super.getLength();
   }

/** get the length of a tuple, call this method if you did 
  *  call setHdr () before
  * @return     size of this tuple in bytes
*/
 
  public short size()
   {
     // return ((short) (fldOffset[fldCnt] - tuple_offset));
         return super.size();
   }
   
 
   /** Copy the tuple byte array out
    *  @return  byte[], a byte array contains the tuple
    *		the length of byte[] = length of the tuple
    */
    
   public byte [] getNodeByteArray() 
   {
	return super.getTupleByteArray();
   }
   
   /** return the data byte array 
    *  @return  data byte array 		
    */

   public byte [] returnNodeByteArray()
   {
       return super.returnTupleByteArray();
   }
  
   public String getLabel() throws Exception
   {
	return super.getStrFld(6);
   }
  
   public Descriptor getDesc() throws Exception
   {
	Descriptor desc = new Descriptor();
	desc.set(super.getIntFld(1),super.getIntFld(2),super.getIntFld(3),super.getIntFld(4),super.getIntFld(5));
	return desc;
   }
   
   public void setLabel(String Label) throws Exception
   {
       this.Label=Label;
       super.setStrFld(6, Label);
       //return this;
   }
  
   public void setDesc(Descriptor Desc) throws Exception
   {
       this.Desc=Desc;
       this.setIntFld(1,Desc.get(0));	
	this.setIntFld(2,Desc.get(1));
	this.setIntFld(3,Desc.get(2));
	this.setIntFld(4,Desc.get(3));
	this.setIntFld(5,Desc.get(4));
       }

 /**
  * Print out the tuple
  * @param type  the types in the tuple
  * @Exception IOException I/O exception
  */
 public void print()
    throws Exception 
 {
   /* AttrType[] type = new AttrType[2];
    type[0]=new AttrType(AttrType.attrDesc);
    type[1]= new AttrType(AttrType.attrString);
    super.print(type);*/
	String str=this.getLabel();
	Descriptor desc = this.getDesc();
	System.out.print(str+" ");
		desc.printDescriptor();
	System.out.println();
 } 

}

