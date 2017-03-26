package testsGraph;

import java.io.*;
import btree.*;
import diskmgr.*;
import edgeheap.*;
import heap.Tuple;
import global.*;
public class BatchEdgeDelete{

    public static void main(String [] ar){
        String edgeFileName = ar[0];
        String graphDBName = ar[1];
        
        EScan eScan = null;
        EID eid  = new EID();
        try{
        graphDB graph = new graphDB();
	//graphDB graph = new graphDB();
        if(!graph.graphDBExists(graphDBName)){
        	graph.openDB(graphDBName);
        }
               
	EdgeHeapFile eFile;

        eFile = new EdgeHeapFile(graph.edgeFileName);
		

        FileReader fr = new FileReader(edgeFileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line!=null){
            String edgeData[] = line.split(" ");
            String edgeLabel = edgeData[2];
            String sourceLabel = edgeData[0];
            String destinationLabel = edgeData[1];
            Edge tuple;
            boolean done = false;
            eScan = eFile.openEScan();
            while (!done){
                tuple = eScan.getNext(eid);
                if(tuple == null)
                    done = true;
                else{
                    if(tuple.getStrFld(2) == edgeLabel){
                        eFile.deleteEdge(eid);
                        break;
                    }
                }
            }
	 line = br.readLine();
            
        }
	System.out.println("Node Count : "+graph.getNodeCnt()+ "\nEdge Count : "+graph.getEdgeCnt()+ "\nPage reads : "+PCounter.rcounter+
                "\nWrite Count : "+PCounter.wcounter);
        }	
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        /*System.out.println("Node Count : "+getNodeCnt()+ "\nEdge Count : "+getEdgeCnt()+ "\nSource Count : "+getSourceCnt()+
                "\nDestination Count : "+getDestCnt()+ "\nLabel Count : "+getLabelCnt()+ "\nPage reads : "+pcounter.rcounter+
                "\nWrite Count : "+pcounter.wcounter);
		*/
    }
}

