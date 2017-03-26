package testsGraph;
import global.*;
import java.io.*;

import heap.Tuple;
import nodeheap.*;
import diskmgr.*;
public class BatchNodeDelete{

    public static void main(String [] ar) {
        String nodeFileName = ar[1];
        String graphDBName = ar[2];

        NID nid  = new NID();
        try{

	graphDB graph = new graphDB();
        if(!graph.graphDBExists(graphDBName)){
        	graph.openDB(graphDBName);
        }
       
        NodeHeapFile nFile = new NodeHeapFile(graph.nodeFileName); //public BTreeFile bFile;
	
      
        FileReader fr = new FileReader(nodeFileName);
        BufferedReader br = new BufferedReader(fr);
        String nodeLabel = br.readLine();
        while(nodeLabel!=null){
            
            NScan nScan = null;
            Node tuple = new Node();
            boolean done = false;
            nScan = nFile.openScan();
            while (!done){
                tuple = nScan.getNext(nid);
                if(tuple == null)
                    done = true;
                else{
                    if(tuple.getLabel().equals(nodeLabel)){
                        nFile.deleteRecord(nid);
			done = true;
                        break;
                    }
                }
            }
        }
	System.out.println("Node Count : "+graph.getNodeCnt()+ "\nEdge Count : "+graph.getEdgeCnt()+ "\nPage reads : "+PCounter.rcounter+
                "\nWrite Count : "+PCounter.wcounter);
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
       /* System.out.println("Node Count : "+getNodeCnt()+ "\nEdge Count : "+getEdgeCnt()+ "\nSource Count : "+getSourceCnt()+
                "\nDestination Count : "+getDestCnt()+ "\nLabel Count : "+getLabelCnt()+ "\nPage reads : "+pcounter.rcounter+
                "\nWrite Count : "+pcounter.wcounter);
	*/
    }
}

