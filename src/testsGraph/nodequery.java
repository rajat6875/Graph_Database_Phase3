package testsGraph;

import java.util.*;
import nodeheap.*;
import edgeheap.*;
import diskmgr.*;
import global.*;
import btree.*;
import index.*;
import heap.*;

//import nodeHeap.*;

public class nodequery
{
	public BTreeFile file;
	public BTFileScan scan;
	public BTreeFile zfile;
	public BTFileScan zscan;

	public static void main(String args[]) throws Exception
	{
		String graphDBName = args[1];
		int numBuf = Integer.parseInt(args[2]);
		int qType = Integer.parseInt(args[3]);
		int index = Integer.parseInt(args[4]);
		
		//graphDB graphdb = new graphDB();

		// Check if dbname already exists in the system catalog else create new db
		/*try{
		if(graphdb.graphDBExists(graphDBName)) // crosscheck : resolved
			graphdb.openDB(graphDBName);
		}
		catch(Exception e)
		{
			System.out.println("Database name does not exist!");
		}*/
		
		// Check if the btree index needs to be used for this query else use heap file. 

		// if btree index needs to be used, the corresponding btree file will be scanned 
		/*if(index==1)
		{
			switch(qType)
			{

				case 1: 
					//Query1();
					break;
				/*case 2: 
					Query2(args);
					break;
				case 3: 
					Query3(args);
					break;
				case 4: 
					Query4(args[5]);
					break;
				case 5: 
					Query5();
					break;
				default: 
					System.out.println("Invalid Query Type");
					break;
			}

		}
		*/
		try{
			// Nikita's code 
		if(qType==1)
		{
			graphDB gdbName=new graphDB(1,graphDBName);
			//gdbName.openDB(graphDBName);
			NodeHeapFile ehf = new NodeHeapFile(gdbName.nodeFileName);
			String fname = gdbName.btreeEdgeWeight;
			int keyType = AttrType.attrInteger;
                	BTreeFile file= new BTreeFile(gdbName.btreeNodeLabel);
                    	BTFileScan scan = file.new_scan(null,null);
                    	KeyDataEntry entry;
                    	entry = scan.get_next();
                    	while(entry!=null)
                    	{
                    		LeafData dc = (LeafData) entry.data;
                    		RID rid = dc.getData();
				NID eid = new NID(rid.pageNo, rid.slotNo);
                    		//RID rid = new RID(pageid.pid, entry.key);
                    		//EID eid = (EID)rid;
                    		Node edge = ehf.getRecord(eid);
				edge.print();
                    		//Edge edge = tuple.getEdge();
                    		// data.add(edge);
                    		 entry = scan.get_next();
                    	}
			gdbName.closeDB();
		}
		else
		{
			graphDB graph=null;
			try{
			 graph = new graphDB();
			if(graph.graphDBExists(graphDBName)) // crosscheck : resolved
			graph.openDB(graphDBName);
			}
			catch(Exception e)
			{
				System.out.println("Database name does not exist!");
			}
			nodequery2.nonindexquery(args);
			graph.closeDB();
		}
			
	}
	catch(Exception e)
	{
		 e.printStackTrace();
            System.err.println ("Error encountered during graphdb tests:\n");
            Runtime.getRuntime().exit(1);
    }
    // Display disk read and write counts for this session 
		System.out.println("Number of disk reads: "+PCounter.rcounter);
		System.out.println("Number of disk writes: "+PCounter.wcounter);

				// close DB after executing the nodequery 
		//System.out.println("Closing "+graphDBName+" database");
		
	} // end main here 

}
