package zIndex;

import java.math.BigInteger;

import btree.KeyClass;
import global.Descriptor;

public class ZKey extends KeyClass{
	
	  private BigInteger key;

	  public String toString(){
	     return key.toString();
	  }

	  /** Class constructor
	   *  @param     value   the value of the integer key to be set 
	   
	  public ZKey(Integer value) 
	  { 
	    key=new BigInteger(value.toString());
	  }*/
	  
	  /** Class constructor
	   *  @param     desc   the descriptor on which index has to be created. 
	   */
	  public ZKey(Descriptor desc){
		 key = ZConvert.getBigIntegerRepresentation(desc);
	  }

	  
	  /** Class constructor
	   *  @param     numString   the value of the integer key to be set
	   *  @param 	 base the value of the base in which numSttring is represented. 
	   */	  
	  public ZKey(String numString, int base){
		  if(base == 2){
			  key = new BigInteger(numString, 2);
		  }
	  }

	  /** Class constructor
	   *  @param     value   the value of the integer key to be set 
	   
	  public ZKey(int value) 
	  { 
	    key=new BigInteger((new Integer(value)).toString());
	  }*/

	  /** get a copy of the integer key
	   *  @return the reference of the copy 
	   */
	  public BigInteger getKey() 
	  {
	    return key;
	  }

	  /** set the integer key value
	   */  
	  public void setKey(BigInteger value) 
	  { 
	    key=new BigInteger(value.toString());
	  }

}
