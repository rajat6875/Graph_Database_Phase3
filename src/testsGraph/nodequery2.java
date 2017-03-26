package testsGraph;

import nodeheap.*;
import edgeheap.*;
import heap.*;
import global.*;
import iterator.*;
import diskmgr.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by nikitashanker on 3/9/17.
 */

class nodeDist{

	public Node node;
	public double distance;

	public nodeDist(){
	    this.node = null;
	    this.distance = 0.0;
	}
	
	public double getDist(){
	return this.distance;	
	}

}


class Comparator3 implements Comparator<Node>{
    public int compare(Node e1, Node e2){
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

class Comparator2 implements Comparator<nodeDist>{
    public int compare(nodeDist e1, nodeDist e2){
	double s1=0.0;
	double s2=0.0;
        try{

	//System.out.pr

        s1 = e1.getDist();
        s2 = e2.getDist();
        }
	catch(Exception e)
	{
	System.out.println("exceptrion");
	}
        if(s1>s2)
	return 1;
	else if(s1<s2)
	return -1;
	else
	return 0;
        
    }
}


public class nodequery2 {

    public static void query0(){

        //open node heapfile
        //scan
        //print node data in the order it occurs in node heap
        NodeHeapFile nhf = null;
        NScan nscan = null;
        NID nid = new NID();
        Tuple tuple = null;
        Node node = null;
        try {
                nhf = new NodeHeapFile(graphDB.nodeFileName); //check what is the fileName used for batch Insert
        }
        catch (Exception e)
        {
            System.err.println (" Could not open Nodeheapfile");
            e.printStackTrace();
        }

        try{
                nscan = nhf.openScan();
        }
        catch (Exception e){
            System.err.println ("***Error Opening NScan");
            e.printStackTrace();
        }

        boolean done = false;

        while(!done){
		Descriptor d = new Descriptor();
		String label = "";


            try {
                //tuple = nscan.getNext(nid);
                node = nscan.getNext(nid);
                if (node == null) {
                    done = true;
		    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
		try{
		     d = node.getDesc();
		}catch (Exception e)
		{e.printStackTrace();}

		try{
		     label = node.getLabel();
		}catch (Exception e)
		{e.printStackTrace();}

		
		

	    try{
		d.printDescriptor();
		System.out.print(" ");
            }catch(Exception e){
                System.err.println("***Error printing the descriptor for Query 2");
                e.printStackTrace();
            }
	    
	    System.out.println(label);

                //node.print(att);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        nscan.closescan();
        System.out.println("------------------QUERY 0 Completed------------------");
    }

    public static void query1(int numBuf){

        //print node data in increasing order of their label
       NodeHeapFile nhf = null;
       NScan nscan = null;
       NID nid = new NID();
       Node node = null;
	ArrayList<Node> data = new ArrayList<Node>();

	try {
                nhf = new NodeHeapFile(graphDB.nodeFileName); //check what is the fileName used for batch Insert
        }
        catch (Exception e)
        {
            System.err.println (" Could not open Nodeheapfile");
            e.printStackTrace();
        }

        try{
                nscan = nhf.openScan();
        }
        catch (Exception e){
            System.err.println ("***Error Opening NScan");
            e.printStackTrace();
        }

        boolean done = false;

	while(!done){


            try {
               
                node = nscan.getNext(nid);
                if (node == null) {
                    done = true;
		    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
		
	data.add(node);

        }
try{
	Collections.sort(data, new Comparator3());
	Node n = null;
	Descriptor d = null;
	String s = "";
        for(int i=0; i<data.size(); i++)
	{
	  n = data.get(i);
		try{
		     d = n.getDesc();
		}catch (Exception e)
		{e.printStackTrace();}

		try{
		     s = n.getLabel();
		}catch (Exception e)
		{e.printStackTrace();}

		try{
		d.printDescriptor();
		System.out.print(" ");
            }catch(Exception e){
                System.err.println("***Error printing the descriptor for Query 2");
                e.printStackTrace();
            }
	    
	    System.out.println(s);
	  
	}
        //data.get(i).print();
        data.clear();
}
catch (Exception e){}
        
	/*FileScan fScan = null;
        Tuple tuple;
        Sort sort = null;
        AttrType[] attrType = new AttrType[2];
        attrType[0] = new AttrType(AttrType.attrDesc);
        attrType[1] = new AttrType(AttrType.attrString);
        TupleOrder order = new TupleOrder(TupleOrder.Ascending);
        short[] attrSize = new short[1];
        attrSize[0] = 10; //size of label is 10

        FldSpec[] projlist = new FldSpec[2];
        RelSpec rel = new RelSpec(RelSpec.outer);
        projlist[0] = new FldSpec(rel, 1);
        projlist[1] = new FldSpec(rel, 2);

        try {
            fScan = new FileScan(graphDB.nodeFileName, attrType, attrSize, (short) 2, 2, projlist, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            sort = new Sort(attrType, (short) 2, attrSize, fScan, 2, order, 10, numBuf, 0, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ///use this sorted list to print the tuples

        tuple = null;
        try{
            tuple = sort.get_next();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        while (tuple !=null){

            try{
            tuple.print(attrType);}
            catch (Exception e){
                e.printStackTrace();
            }

            try{
                tuple = sort.get_next();}
            catch (Exception e){
                e.printStackTrace();
            }
        }

        try {
            sort.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        System.out.println("------------------QUERY 1 Completed------------------");
    }

    public static void query2(Descriptor targetDesc, int numBuf){

        //take 5D target descriptor and print nodes in order of increasing distance from the target descr
        //need to modify header func setHdr for Descriptor as well
        NodeHeapFile nhf = null;
        NScan nscan = null;
        NID nid = new NID();
       // Tuple tuple = null;
        Node node = null;
        //Heapfile tempf = null;
        //int strSize = 10;
	nodeDist nd = new nodeDist();
	ArrayList<nodeDist> data1 = new ArrayList<nodeDist>();

        try {
            nhf = new NodeHeapFile(graphDB.nodeFileName); //check what is the fileName used for batch Insert
        }
        catch (Exception e)
        {
            System.err.println (" Could not open Nodeheapfile");
            e.printStackTrace();
        }

        try{
            nscan = nhf.openScan();
        }
        catch (Exception e){
            System.err.println ("***Error Opening NScan");
            e.printStackTrace();
        }

       
        boolean done = false;
        while(!done){
            try{
                //tuple = nscan.getNext(nid);
                node = nscan.getNext(nid);
                if (node == null)
              {      done = true;
		break;
		}
            }catch (Exception e){
                e.printStackTrace();
            }

 	  Descriptor descriptor = new Descriptor();

           /* String label = "";
          

            try{
                label = node.getLabel();
            }catch (Exception e){
                e.printStackTrace();
            }
*/
            try{
                descriptor = node.getDesc();
            }catch (Exception e){
                e.printStackTrace();
            }

            double dist = descriptor.distance(targetDesc);

	    nd.node = node;
	    nd.distance = dist;
	//System.out.println(dist);
		try{
	//node.print();
	}
	catch(Exception e){ }
	    data1.add(nd);


/*            try {
                t.setDescFld(1,descriptor);
                t.setStrFld(2,label);
                t.setFloFld(3,dist);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
		data1.add(t);
            }catch (Exception e){
                e.printStackTrace();
            }
*/
        }
	//nodeDist nd1 = null;
	
	//System.out.println(data1.size());
	try{
	Collections.sort(data1, new Comparator2());
	
	//Node n = null;
	Descriptor d = null;
	String s = "";

        for(int i=0; i<data1.size(); i++)
	{
	  nodeDist nd1 = data1.get(i);
	  Node n = nd1.node;
	System.out.println("in loop");
		n.print();

		/*try{
		     d = n.getDesc();
		}catch (Exception e)
		{e.printStackTrace();}

		try{
		     s = n.getLabel();
		}catch (Exception e)
		{e.printStackTrace();}

		try{
		d.printDescriptor();
		System.out.print(" ");
            }catch(Exception e){
                System.err.println("***Error printing the descriptor for Query 2");
                e.printStackTrace();
            }*/
	    
//	    System.out.println(s);
		
	  
	}
        //data.get(i).print();
        data1.clear();
	}
	catch (Exception e){
	//System.out.println("error");	
	}
	

        System.out.println("------------------QUERY 2 Completed------------------");
    }

    public static void query3(Descriptor targetDesc, int numBuf, double targetDist){

        //take target descriptor and distance
        //scan the node heapfile
        //for each node measure the distance between the node descriptor and target.
        //if distance = target distance, then print it.
        NodeHeapFile nhf = null;
        NScan nscan = null;
        NID nid = new NID();
        Tuple tuple = null;
        Node node = null;


        try {
            nhf = new NodeHeapFile(graphDB.nodeFileName); //check what is the fileName used for batch Insert
        }
        catch (Exception e)
        {
            System.err.println (" Could not open Nodeheapfile");
            e.printStackTrace();
        }

        try{
            nscan = nhf.openScan();
        }
        catch (Exception e){
            System.err.println ("***Error Opening NScan");
            e.printStackTrace();
        }

        boolean done = false;

        while(!done){

            try {
               // tuple = nscan.getNext(nid);
                node = nscan.getNext(nid);
                if (node == null) {
                    done = true;
		 break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            Descriptor desc = null;
            double dist = 0.0;

            try {
               // desc = tuple.getDescFld(1);
                desc = node.getDesc();
            } catch (Exception e) {
                e.printStackTrace();
            }

	/*try {
                desc.printDescriptor();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
	    

            dist = desc.distance(targetDesc);
	    //System.out.println(dist);

            if (dist <= targetDist){
                try{
                    desc.printDescriptor();
                }catch (IOException e){
                    System.err.println("****Error printing Node Descriptor for Query 3 ");
                    e.printStackTrace();
                }

                try{
                    System.out.println(" "+ node.getLabel());

                }catch (Exception e){
                    System.err.println("****Error printing Node Label for Query 3 ");
                    e.printStackTrace();
                }
            }
        }

        nscan.closescan();
        System.out.println("------------------QUERY 3 Completed------------------");

    }

    public static void query4(String targetLabel)
    throws IOException{
        EdgeHeapFile ehf = null;
        EScan escan = null;
        EID eid = new EID();
        Tuple tuple = null;
        NodeHeapFile nhf = null;

        try{
            nhf = new NodeHeapFile(graphDB.nodeFileName);
        }catch (Exception e){
            System.err.println (" Could not open Nodeheapfile");
            e.printStackTrace();
        }

        try {
            ehf = new EdgeHeapFile(graphDB.edgeFileName); //check what is the fileName used for batch Insert
        }
        catch (Exception e)
        {
            System.err.println (" Could not open Edgeheapfile");
            e.printStackTrace();
        }

        try{
            escan = ehf.openEScan();
        }
        catch (Exception e){
            System.err.println ("***Error Opening NScan");
            e.printStackTrace();
        }

        boolean done = false;
        Edge currEdge = null;
        NID srcNID = null;
        NID destNID = null;
        //Tuple srcTupleNd = null;
        //Tuple dstTupleNd = null;
        Node srcNode = null;
        Node dstNode = null;
        Descriptor descriptor = new Descriptor();
        boolean descSet = false;
        ArrayList<String> arrSource = new ArrayList<String>();
        ArrayList<String> arrDest = new ArrayList<String>();
        String addStr = "";

        while(!done){

            try {
                //tuple = escan.getNext(eid);
                currEdge = escan.getNext(eid);
                if (currEdge == null) {
                    done = true;
		    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

//            try{
//                currEdge = new Edge(tuple.getTupleByteArray(),tuple.getOffset());
//            }catch (Exception e){
//                e.printStackTrace();
//            }

            //get source and destination NIDs
            try{
                srcNID = currEdge.getSource();
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                destNID = currEdge.getDestination();
            }catch (Exception e){
                e.printStackTrace();
            }

            //get source and destination nodes
            try{
                srcNode = nhf.getRecord(srcNID);
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                dstNode = nhf.getRecord(destNID);
            }catch (Exception e){
                e.printStackTrace();
            }

            String srcLabel = "";
            String dstLabel = "";
            if (srcNode != null){
                    try{
                        srcLabel = srcNode.getLabel();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }

            if (dstNode != null){
                try{
                    dstLabel = dstNode.getLabel();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            try{
                if(srcLabel.equals(targetLabel)){
                    addStr = "Edge label: " + currEdge.getLabel() + " ; Edge Weight: "+ currEdge.getWeight().toString();
                    arrSource.add(addStr);

                    if (!descSet){
                        try{
                            descriptor = srcNode.getDesc();
                            descSet = true;}
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                if(dstLabel.equals(targetLabel)){
                    addStr = "Edge label: " + currEdge.getLabel() + " ; Edge Weight: "+ currEdge.getWeight().toString();
                    arrDest.add(addStr);

                    if (!descSet){
                        try{
                            descriptor = dstNode.getDesc();
                            descSet = true;}
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("Node Label: "+targetLabel);
        System.out.print("Node Descriptor: ");
        try{
        descriptor.printDescriptor();}
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Outgoing Edges for the node: ");
        for (int i =0;i<arrSource.size();i++){
            System.out.println(arrSource.get(i));
        }

        System.out.println("Incoming Edges for the node: ");
        for(int i=0;i<arrDest.size();i++){
            System.out.println(arrDest.get(i));
        }

        escan.closescan();

    }

    public static void query5(Descriptor targetDesc, double targetDist){

        NodeHeapFile nhf = null;
        NScan nscan = null;
        NID nid = new NID();
        Tuple tuple = null;
        Node node = null;
        ArrayList<String> arrLabels = new ArrayList<String>();

        try {
            nhf = new NodeHeapFile(graphDB.nodeFileName); //check what is the fileName used for batch Insert
        }
        catch (Exception e)
        {
            System.err.println (" Could not open Nodeheapfile");
            e.printStackTrace();
        }

        try{
            nscan = nhf.openScan();
        }
        catch (Exception e){
            System.err.println ("***Error Opening NScan");
            e.printStackTrace();
        }

        boolean done = false;

        while(!done){

            try {
               // tuple = nscan.getNext(nid);
                node = nscan.getNext(nid);
                if (node == null) {
                    done = true;
		    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            Descriptor desc = null;
            double dist = 0.0;

            try {
                desc = node.getDesc();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
                dist = desc.distance(targetDesc);
            }catch(Exception e){
                e.printStackTrace();
            }

            String label = "";

            if (dist == targetDist){

                try{
                    label = node.getLabel();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                if(label !=null)
                 arrLabels.add(label);
            }
        }

        for (int i=0;i<arrLabels.size();i++){
            try{
                query4(arrLabels.get(i));
                System.out.println();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        nscan.closescan();
        System.out.println("------------------QUERY 5 Completed------------------");

    }

    public static void nonindexquery(String [] argvs) {

        try{
            //in case INDEX !=1


            int numBuf = Integer.parseInt(argvs[2]);
            int qType = Integer.parseInt(argvs[3]);
            int[] tempDesc = new int[5];
            double targetDist = 0.0;

            Descriptor targetDesc = null;

            if(qType == 2 || qType == 3 || qType == 5){

                targetDesc = new Descriptor();
                if(argvs[5] !=null && !argvs[5].isEmpty()){

                    for (int i=0;i<5;i++){
                        try{
                            tempDesc[i] = Integer.parseInt(argvs[i+5]);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                try{
                    targetDesc.set(tempDesc[0],tempDesc[1],tempDesc[2],tempDesc[3],tempDesc[4]);}
                catch(Exception e){ e.printStackTrace();}
            }

            switch(qType){

                case 0:
                    query0();
                    break;
                case 1:
                    query1(numBuf);
                    break;
                case 2:
                    if (targetDesc!=null)
                        query2(targetDesc,numBuf);
                    break;
                case 3:
                    if (targetDesc!=null){
                        if (argvs[10]!=null || !argvs[10].isEmpty())
                            targetDist = Double.parseDouble(argvs[10]);

                        query3(targetDesc,numBuf,targetDist);
                    }
                    break;
                case 4:
                    try{
                        query4(argvs[5]);
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    System.out.println("------------------QUERY 4 Completed------------------");
                    break;
                case 5:
                    if (targetDesc!=null){
                        if (argvs[10]!=null || !argvs[10].isEmpty())
                            targetDist = Double.parseDouble(argvs[10]);
                        query5(targetDesc,targetDist);
                    }
                    break;
                default:
                    break;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println ("Error encountered during Node Query tests:\n");
            Runtime.getRuntime().exit(1);
        }
    }
}
