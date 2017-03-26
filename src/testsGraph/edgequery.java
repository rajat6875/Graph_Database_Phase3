package testsGraph;

import java.util.*;
import diskmgr.*;
import heap.*;
import bufmgr.*;
import edgeheap.*;
import global.*;
import nodeheap.*;
import btree.*;
import java.io.*;

class Comparator1 implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
	String s1="";
	String s2="";
	try{
        NID nid1 = e1.getSource();
        NID nid2 = e2.getSource();
	//nid = tuple.getDestination();
	NodeHeapFile nfile = new NodeHeapFile(graphDB.nodeFileName); 
	Node node1=nfile.getRecord(nid1);
	Node node2=nfile.getRecord(nid2);
      s1 =node1.getLabel();
        s2 = node2.getLabel();
        }
	catch(Exception ex){
	}
        return s1.compareTo(s2);
        
    }
}

class Comparator2 implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
	String s1="";
	String s2="";
	try{
        NID nid1 = e1.getDestination();
        NID nid2 = e2.getDestination();
        NodeHeapFile nfile = new NodeHeapFile(graphDB.nodeFileName); 
	Node node1=nfile.getRecord(nid1);
	Node node2=nfile.getRecord(nid2);
       s1 = node1.getLabel();
       s2 = node2.getLabel();
       }
	catch(Exception e)
	{

	} 
        return s1.compareTo(s2);
        
    }
}

class Comparator3 implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
	String s1="";
	String s2="";
        try{
        s1 = e1.getLabel();
        s2 = e2.getLabel();
        }
	catch(Exception e)
	{
	}
        return s1.compareTo(s2);
        
    }
}

class Comparator4 implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
	Integer w1=0;
	Integer w2=0;
        try{
        w1 = e1.getWeight();
        w2 = e2.getWeight();
     	}
   	catch(Exception e)
	{
	}
        if(w1<w2)
        return -1;
        if(w1==w2)
        return 0;
        else
        return 1;
        
    }
}

public class edgequery{

     public static void main(String []args){
        String graphDBName = args[1];
        int numbuf = Integer.parseInt(args[2]);
        int qtype = Integer.parseInt(args[3]);
        int index = Integer.parseInt(args[4]);
        int lbound=0;
        int ubound=0;
	EID eid=null;
	RID rid=null;
	NID nid=null;
	Edge edge=null;
	 EScan eScan=null;
        Tuple tuple=null;
	EdgeHeapFile  ehf=null;
	ArrayList<Edge> data=new ArrayList<Edge>();
	BTreeFile file=null;
	BTFileScan scan=null;
	String fname="";
	LeafData dc=null;
	boolean done=false;
        if(qtype==5){
            lbound = Integer.parseInt(args[5]);
            ubound = Integer.parseInt(args[6]);
        }
        graphDB gdbName=null;
	BTreeFile btfNode1 = null;
	KeyClass key = null;
	BTreeFile btfNode2 = null;
	int keyType;	
	try{
	/*keyType = AttrType.attrString;
              		 btfNode1 = new BTreeFile(gdbName.btreeEdgeLabel,keyType,10,1);
           		
                	 eScan = ehf.openEScan();
                	 eid =  new EID();
                	//Edge edge;
                	done=false;
                	while(!done){
                		edge = eScan.getNext(eid);
                		if(edge==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.getRecord(tuple);
                		key = new StringKey(edge.getLabel());
                		btfNode1.insert(key,eid);
                		//System.out.println(tuple.getLabel());
                	}

	keyType = AttrType.attrInteger;
                	btfNode2 = new BTreeFile(gdbName.btreeEdgeWeight,keyType,10,1);
           
                	 eScan = ehf.openEScan();
                	 eid =  new EID();
                	//Edge tuple;
                	done=false;
                	while(!done){
                		edge = eScan.getNext(eid);
                		if(edge==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.getRecord(tuple);
                		key = new IntegerKey(edge.getWeight());
                		btfNode2.insert(key,eid);
                		
                	}*/
	
        /* Get graphDBObject using graphDBName
        Let us assume gdbName for now */
        
        /*String EdgeHeapFileName = gdbName.EdgeFileName;
        EdgeHeapFile ehf;
        try{
            ehf = new EdgeHeapFile(EdgeHeapFileName);
        }
        catch(Exception e){
            System.out.println("Could not open heapFile");
            e.printStackTrace();
        }*/
	
        switch(qtype){
	
            case 0:
		 try{
			gdbName=new graphDB();
			gdbName.openDB(graphDBName);
		       ehf = new EdgeHeapFile(gdbName.edgeFileName);
                        eScan = ehf.openEScan();
                    }
                    catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                    eid = new EID();
                    tuple = new Tuple();
                    edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        edge.print();
                    }
		    gdbName.closeDB();
                    break;
                    
            case 1:
			
	 	 ehf = new EdgeHeapFile(graphDB.edgeFileName);
                     data = new ArrayList<Edge>();
		// EScan eScan;
		   if(index==0){
			gdbName=new graphDB();
		gdbName.openDB(graphDBName);
		
                    try{
                    eScan = ehf.openEScan();
                    }
                    catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                    eid = new EID();
                     tuple = new Tuple();
                     edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        data.add(edge);
                    }
                    Collections.sort(data, new Comparator1());
                    for(int i=0; i<data.size(); i++)
                    data.get(i).print();
			data.clear();
                    }
                    else
                    {
			gdbName=new graphDB(2,graphDBName);
			//gdbName.openDB(graphDBName);
			//   ehf = new EdgeHeapFile(graphDB.edgeFileName);
                    	// data = new ArrayList<Edge>();
                    	// fname = graphDB.btreeEdgeLabel;
			keyType = AttrType.attrString;
              		 //btfNode1 = new BTreeFile(graphDB.btreeEdgeLabel);
           		/*
                	 eScan = ehf.openEScan();
                	 eid =  new EID();
                	//Edge edge;
                	done=false;
                	while(!done){
                		edge = eScan.getNext(eid);
                		if(edge==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.getRecord(tuple);
                		key = new StringKey(edge.getLabel());
                		btfNode1.insert(key,eid);
                		//System.out.println(tuple.getLabel());
                	}
			
			System.out.println(gdbName.btreeEdgeLabel);
                    	*/ 
			file = new BTreeFile(graphDB.btreeEdgeLabel);
			//BT.printAllLeafPages(file.getHeaderPage());                    	
			scan = file.new_scan(null,null);
                    	KeyDataEntry entry;
                    	entry = scan.get_next();
			//System.out.println(entry.data);
                    	while(entry!=null)
                    	{
                    		 dc = (LeafData) entry.data;
                    		rid = dc.getData();
				eid = new EID(rid.pageNo, rid.slotNo);
                    		//RID rid = new RID(pageid.pid, entry.key);
                    		//EID eid = (EID)rid;
                    		 edge = ehf.getRecord(eid);
				//System.out.println(edge.getLabel());
                    		//Edge edge = tuple.getEdge();
                    		 data.add(edge);
                    		 entry = scan.get_next();
                    	}
                    	Collections.sort(data, new Comparator1());
                    	for(int i=0; i<data.size(); i++)
                    	data.get(i).print();
                    	data.clear();
			gdbName.closeDB();
                    }
                    break;
            
            case 2: if(index==0){
			  	gdbName=new graphDB();
		gdbName.openDB(graphDBName);
	 	  ehf = new EdgeHeapFile(gdbName.edgeFileName);
                
             	    data = new ArrayList<Edge>();
                    try{
                   	eScan = ehf.openEScan();
                    }
                    catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                     eid = new EID();
                   // Tuple tuple = new Tuple();
                     edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        data.add(edge);
                    }
                    Collections.sort(data, new Comparator2());
                    for(int i=0; i<data.size(); i++)
                    data.get(i).print();
			data.clear();
			gdbName.closeDB();
                    }
                    else
                    {
			gdbName=new graphDB(2,graphDBName);
			//gdbName.openDB(graphDBName);
			 ehf = new EdgeHeapFile(gdbName.edgeFileName);
                    	
                    	 data = new ArrayList<Edge>();
                    	fname = gdbName.btreeEdgeLabel;
			keyType = AttrType.attrString;
              		file = new BTreeFile(fname);
                    	scan = file.new_scan(null,null);
                    	KeyDataEntry entry;
                    	entry = scan.get_next();
                    	while(entry!=null)
                    	{
                    		
                    		 dc = (LeafData) entry.data;
                    		rid = dc.getData();
				eid = new EID(rid.pageNo, rid.slotNo);
                    		//RID rid = new RID(pageid.pid, entry.key);
                    		//EID eid = (EID)rid;
                    		 edge = ehf.getRecord(eid);
                    		//Edge edge = tuple.getEdge();
                    		 data.add(edge);
                    		 entry = scan.get_next();
                    	}
                    	Collections.sort(data, new Comparator2());
                    	for(int i=0; i<data.size(); i++)
                    	data.get(i).print();
			gdbName.closeDB();
                    }
                    break;
                    
            case 3: if(index==0){
            	     data = new ArrayList<Edge>();
		gdbName=new graphDB();
			gdbName.openDB(graphDBName);
			   ehf = new EdgeHeapFile(gdbName.edgeFileName);
                    	
                    try{
                    eScan = ehf.openEScan();
                    }
                    catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                     eid = new EID();
                     tuple = new Tuple();
                     edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        data.add(edge);
                    }
                    Collections.sort(data, new Comparator3());
                    for(int i=0; i<data.size(); i++)
                    data.get(i).print();
			data.clear();
			gdbName.closeDB();
                    }
                  else
                    {
			gdbName=new graphDB(2,graphDBName);
			//gdbName.openDB(graphDBName);
			   ehf = new EdgeHeapFile(gdbName.edgeFileName);
                    	fname = gdbName.btreeEdgeLabel;
			
                    	file  = new BTreeFile(fname);
			BT.printAllLeafPages(file.getHeaderPage());
                    	 scan = file.new_scan(null,null);
                    	KeyDataEntry entry;
                    	entry = scan.get_next();
                    	while(entry!=null)
                    	{
                    		 dc = (LeafData) entry.data;
                    		rid = dc.getData();
				eid = new EID(rid.pageNo, rid.slotNo);
                    		//RID rid = new RID(pageid.pid, entry.key);
                    		//EID eid = (EID)rid;
                    		 edge = ehf.getRecord(eid);
				 edge.print();
                    		//Edge edge = tuple.getEdge();
                    		 //data.add(edge);
                    		 entry = scan.get_next();
                    	}
                    	
                    }
                    break;
                    
            case 4: if(index==0){
             	     data = new ArrayList<Edge>();
			gdbName=new graphDB();
			gdbName.openDB(graphDBName);
			   ehf = new EdgeHeapFile(gdbName.edgeFileName);
                    	
                    try{
                    eScan = ehf.openEScan();
                    }
                    catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                     eid = new EID();
			tuple = new Tuple();
                     edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        data.add(edge);
                    }
                    Collections.sort(data, new Comparator4());
                    for(int i=0; i<data.size(); i++)
                    data.get(i).print();
			data.clear();
                    }
                   else
                    {
			gdbName=new graphDB(3,graphDBName);
			//gdbName.openDB(graphDBName);
			ehf = new EdgeHeapFile(gdbName.edgeFileName);
			fname = gdbName.btreeEdgeWeight;
			keyType = AttrType.attrInteger;
                	file = new BTreeFile(fname);
                    	scan = file.new_scan(null,null);
                    	KeyDataEntry entry;
                    	entry = scan.get_next();
                    	while(entry!=null)
                    	{
                    		 dc = (LeafData) entry.data;
                    		rid = dc.getData();
				eid = new EID(rid.pageNo, rid.slotNo);
                    		//RID rid = new RID(pageid.pid, entry.key);
                    		//EID eid = (EID)rid;
                    		 edge = ehf.getRecord(eid);
				edge.print();
                    		//Edge edge = tuple.getEdge();
                    		// data.add(edge);
                    		 entry = scan.get_next();
                    	}
			gdbName.closeDB();
                    }
                    break;
                    
            case 5: if(index==0|index==1){
			gdbName=new graphDB();
			gdbName.openDB(graphDBName);
			   ehf = new EdgeHeapFile(gdbName.edgeFileName);
			
            	    try{
                   		eScan = ehf.openEScan();
                    }
                    catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                     eid = new EID();
                     edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        {   
                            if((edge.getWeight()>=lbound) && (edge.getWeight()<=ubound))
                            edge.print();
                        }
                    }
                    }
                  /*  else
                    {
			gdbName=new graphDB(3);
			gdbName.openDB(graphDBName);
			   ehf = new EdgeHeapFile(gdbName.edgeFileName);
                    	 fname = gdbName.btreeEdgeWeight;
			
	keyType = AttrType.attrInteger;
                	btfNode2 = new BTreeFile(gdbName.btreeEdgeWeight,keyType,10,1);
           
                	 eScan = ehf.openEScan();
                	 eid =  new EID();
                	//Edge tuple;
                	done=false;
                	while(!done){
                		edge = eScan.getNext(eid);
                		if(edge==null){
                			done=true;
                			break;		
                		}
                		//Tuple node=efile.getRecord(tuple);
                		key = new IntegerKey(edge.getWeight());
                		btfNode2.insert(key,eid);
                		

                	}
                    	 file = new BTreeFile(fname);
                    	KeyClass lo_key = new IntegerKey(lbound);
                    	KeyClass hi_key = new IntegerKey(ubound);
                    	 scan = btfNode2.new_scan(lo_key,hi_key);
                    	KeyDataEntry entry;
                    	entry = scan.get_next();
                    	while(entry!=null)
                    	{
                    		 dc = (LeafData) entry.data;
                    		rid = dc.getData();
				eid = new EID(rid.pageNo, rid.slotNo);
                    		//RID rid = new RID(pageid.pid, entry.key);
                    		//EID eid = (EID)rid;
                    		 edge = ehf.getRecord(eid);
                    		//Edge edge = tuple.getEdge();
                    		 data.add(edge);
                    		 entry = scan.get_next();
                    	}
                    }*/
                    break;        
		case 6: 
                   gdbName=new graphDB();
			gdbName.openDB(graphDBName);
			   ehf = new EdgeHeapFile(gdbName.edgeFileName);
			
            	    try{
                   		eScan = ehf.openEScan();
                    }
			  catch(Exception e){
                        System.out.println("Error opening Scan\n");
                        e.printStackTrace();
                    }
                     eid = new EID();
                     tuple = new Tuple();
                     edge = new Edge();
                     done = false;
                    
                    while(!done){
                        try{
                            edge = eScan.getNext(eid);
                            
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        //edge = tuple.getEdge();
                        if(edge==null)
                            done=true;
                        else
                        data.add(edge);
                    }
                    File f1 = null;
			FileWriter fw1 = null;			
			try{
			f1 = new File("query6_data.txt");
			fw1 = new FileWriter(f1);
			}
			catch(Exception e){ }
                    for(int i=0; i<data.size(); i++)
                    {
                    
                    Edge edge1 = data.get(i);
                    NID did = edge1.getDestination();
                    for(int j=0; j<data.size(); j++){
                    Edge edge2 = data.get(j);
                    NID sid = edge2.getSource();
		    //System.out.println("chandan");
                    if(did.equals(sid))
                    {		
			
	String s = edge1.getLabel() + " pair" + edge2.getLabel();
	fw1.write(s);

				System.out.println("**************");
				edge1.print();
						System.out.println("> pairs with <");
				edge2.print();
				System.out.println("**************");
			}
                    }
                    
                    }
                    break;    
        }
	System.out.println("Number of disk reads: "+PCounter.rcounter);
		System.out.println("Number of disk writes: "+PCounter.wcounter);
		//fw1.close();
		//f1.close();		
}	
	catch(Exception ex)	{
	}
     }
}
