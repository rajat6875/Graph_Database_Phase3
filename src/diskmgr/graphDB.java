
/* File DB.java */

package diskmgr;

import java.io.*;
import java.util.*;
import btree.*;
import edgeheap.*;
import global.*;
import nodeheap.*;
import nodeheap.HFBufMgrException;
import nodeheap.HFDiskMgrException;
import nodeheap.HFException;
import nodeheap.InvalidSlotNumberException;
import nodeheap.InvalidTupleSizeException;
import heap.*;
public class graphDB implements GlobalConst {

  
  private static final int bits_per_page = MAX_SPACE * 8;
  
   public static final String nodeFileName = "nodeHeap.in";
   public static final String edgeFileName = "edgeHeap.in";    
   public static final String btreeNodeLabel = "btreeNodeLabel";
   public static final String btreeEdgeLabel = "btreeEdgeLabel";  
   public static final String btreeEdgeWeight = "btreeEdgeWeight";
   public SystemDefs sysDefs=null;
   public static BTreeFile btfedge=null;

   
      //constructor for graphDB
    //"type" denotes the different indexing and clustering strategies used for graph dbase
    public graphDB(int type, String dbName) throws Exception {
	boolean done=false;
    NodeHeapFile nfile = new NodeHeapFile(nodeFileName);
   	 EdgeHeapFile efile = new EdgeHeapFile(edgeFileName);
   	 int keyType=0;
   	 KeyClass key;
        //add node heap file
        //implement switch for the type. ask Parth for the clarification
        
        	switch(type){
            //BTree for Node Labels
        	case 1:
		this.openDB(dbName); 
		try {
            	keyType = AttrType.attrString;
		
                BTreeFile btfNode = new BTreeFile(this.btreeNodeLabel,keyType,10,1);
               // boolean done=false;
                done=false;
                	NScan escan = nfile.openScan();
                	NID nid =  new NID();
                	Node tuple;
                	while(!done){
                		tuple = escan.getNext(nid);
                		if(tuple==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.get(nid);
                		key = new StringKey(tuple.getLabel());
                		btfNode.insert(key,nid);
                	}
		btfNode.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                Runtime.getRuntime().exit(1);
            }
                break;

            //BTree for Edge Labels
            case 2:
		//System.out.println("Kandan Chawa");
	      this.openDB(dbName);
              try {
                keyType = AttrType.attrString;
                this.btfedge = new BTreeFile(this.btreeEdgeLabel,keyType,10,1);
           
                	EScan escan = efile.openEScan();
                	EID eid =  new EID();
                	Edge tuple;
                	done=false;
                	while(!done){
                		tuple = escan.getNext(eid);
                		if(tuple==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.getRecord(tuple);
                		key = new StringKey(tuple.getLabel());
                		this.btfedge.insert(key,eid);
				
                		//System.out.println(tuple.getLabel());
                	}
		this.btfedge.close();
		// BT.printAllLeafPages(this.btfedge.getHeaderPage());
		
            } catch (Exception e1) {
                e1.printStackTrace();
                Runtime.getRuntime().exit(1);
            }
	  // this.closeDB();
	   break;
            //create ztree for Descriptors

            //BTree for edge weights
            case 3:
            	try {
		this.openDB(dbName);
                keyType = AttrType.attrInteger;
                BTreeFile btfNode2 = new BTreeFile(this.btreeEdgeWeight,keyType,10,1);
           
                	EScan escan = efile.openEScan();
                	EID eid =  new EID();
                	Edge tuple;
                	done=false;
                	while(!done){
                		tuple = escan.getNext(eid);
                		if(tuple==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.getRecord(tuple);
                		key = new IntegerKey(tuple.getWeight());
                		btfNode2.insert(key,eid);
                		
                	}
		btfNode2.close();
            } catch (Exception e1) {
                e1.printStackTrace();
                Runtime.getRuntime().exit(1);
            }
            //TODO:
            //1. scan the node heap file and add node labels to the btree
            //2. scan edge heap file and add edge labels to the btree
            //3. create the ztree on descriptors
            //4. scan the edge and add edge weights to the btree

        }
    }

    public boolean graphDBExists(String fname){
	boolean exists=false;
        try{
		sysDefs.JavabaseDB.openDB(fname);
		exists=true;
	}
	catch(Exception ex)
	{
		exists=false;
	}
	
	return exists;
	
    }

     public int getNodeCnt() throws HFException, HFBufMgrException, HFDiskMgrException, IOException, InvalidSlotNumberException, InvalidTupleSizeException{
    	 NodeHeapFile nfile = new NodeHeapFile(nodeFileName);
       	 
       //call the function of Node to return the node cnt
        return nfile.getNodeCnt();
     }

    public int getEdgeCnt() throws EHFException, EHFBufMgrException, EHFDiskMgrException, IOException, edgeheap.InvalidSlotNumberException, edgeheap.InvalidTupleSizeException{
    	EdgeHeapFile efile = new EdgeHeapFile(edgeFileName);
         return efile.getRecCnt();
     }

    public int getSourceCnt() throws HFException, HFBufMgrException, HFDiskMgrException, IOException, EHFException, EHFBufMgrException, EHFDiskMgrException{
    	NodeHeapFile nfile = new NodeHeapFile(nodeFileName);
    	 EdgeHeapFile efile = new EdgeHeapFile(edgeFileName);
	HashSet hs = new HashSet();
         //get the nodes from NodeHeapFile and add them to a set to get distinct labels
         //return the count of distinct labels
        //return distinct source nodes - from edges
    	 try{
    		 EScan escan = efile.openEScan();
    		 EID eid = new EID();
    		 NID nid =  new NID();
    		 Tuple tuple = new Tuple();
    		 boolean done = false;
    		 while(!done){
    			 tuple = escan.getNext(eid);
    			 if(tuple==null){
    				 done=true;
    				 break;		
    			 }
    			 nid.pageNo.pid=tuple.getIntFld(1);
    			 nid.slotNo=tuple.getIntFld(2);
    			 heap.Tuple node=nfile.getRecord(nid);
			hs.add(node.getStrFld(2));
		
    		 }
	}
	catch(Exception ex){
		ex.printStackTrace();	
	}
	
         return hs.size();
     }

    public int getDestinationCnt() throws HFException, HFBufMgrException, HFDiskMgrException, IOException, EHFException, EHFBufMgrException, EHFDiskMgrException{

         //return distinct no. of destination nodes. - from edge
	  //return distinct source nodes - from edges
    	NodeHeapFile nfile = new NodeHeapFile(nodeFileName);
   	 EdgeHeapFile efile = new EdgeHeapFile(edgeFileName);
	HashSet<String> ds = new HashSet<String>();
        //get the nodes from NodeHeapFile and add them to a set to get distinct labels
        //return the count of distinct labels
       //return distinct source nodes - from edges
	try{
	EScan escan = efile.openEScan();
	EID eid = new EID();
	PageId pgno= new PageId();
	NID nid;
	Edge tuple;
	boolean done = false;
	while(!done){
		tuple = escan.getNext(eid);
		if(tuple==null){
			done=true;
			break;		
		}
	//tuple.getLabel();
		
		nid = tuple.getDestination(); 
		Node node=nfile.getRecord(nid);
		ds.add(node.getLabel());
		//node.print();
	}
	}
	catch(Exception ex){
		ex.printStackTrace();	
	}
	
        return ds.size();
     }

    public int getLabelCnt(){
        //return distinct labels in database
        return 0;
    }

  /** Open the database with the given name.
   *
   * @param name DB_name
   *
   * @exception IOException I/O errors
   * @exception FileIOException file I/O error
   * @exception InvalidPageNumberException invalid page number
   * @exception DiskMgrException error caused by other layers
   */
  public void openDB( String fname)
    throws IOException, 
	   InvalidPageNumberException, 
	   FileIOException,
	   DiskMgrException {
        if(graphDBExists(fname))
        {
		System.out.println("Opening the database");
		sysDefs.JavabaseDB.openDB(fname);
	}
        else
        {
		System.out.println("Creating the database");
        	sysDefs = new SystemDefs( fname, 8193,  100, "Clock" );
        }
    }
  
  /** default constructor.
   */
  public graphDB() { }
  
  
  
  /** Close DB file.
   * @exception IOException I/O errors.
   */
  public void closeDB() throws IOException {
    sysDefs.JavabaseDB.closeDB();
  }
  
  
  /** Destroy the database, removing the file that stores it. 
   * @exception IOException I/O errors.
   */
  public void DBDestroy() 
    throws IOException {
    
    sysDefs.JavabaseDB.DBDestroy();
  }
  
}


