package zIndex;

import java.math.BigInteger;

import global.Descriptor;

public class ZConvert {
	//  Method to convert a descriptor to binary using bit shuffling algorithm for calculating Z values.  
  public static String getBinary(Descriptor desc){
	  String binaryString = "";
	  for(int i =0;i<32;i++){
		  String cur = "";
		  for(int j=0;j<5;j++){
			  cur = (desc.get(j)&1<<i)+""+cur;
		  }
		  binaryString = cur+binaryString;
	  }
	  return binaryString;
  }
  
  //Method to return a BigInteger for a descriptor.
  public static BigInteger getBigIntegerRepresentation(Descriptor desc){
	  return new BigInteger( getBinary(desc), 2);
  }
  
}
