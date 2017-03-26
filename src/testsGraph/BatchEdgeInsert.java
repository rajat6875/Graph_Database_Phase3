package testsGraph;

import java.io.*;
import global.*;
import diskmgr.*;
import edgeheap.*;
import heap.*;
import nodeheap.*;
public class BatchEdgeInsert{

    public static void main(String [] ar){
        String edgeFileName = ar[1];
        String graphDBName = ar[2];
        try{
        graphDB graph = new graphDB();
        if(!graph.graphDBExists(graphDBName)){
        	graph.openDB(graphDBName);
        }
        
        
        EdgeHeapFile eFile = new EdgeHeapFile(graph.edgeFileName);
        NID nid  = new NID();
        NodeHeapFile nFile = new NodeHeapFile(graph.nodeFileName);
        FileReader fr = new FileReader(edgeFileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line!=null){

           // String line = br.readLine();
            String[]  edgeData = line.split(" ");
            String edgeLabel = edgeData[2];
            int edgeWeight = Integer.parseInt(edgeData[3]);
            String sourceLabel = edgeData[0];
            String destinationLabel = edgeData[1];

            NScan eScan = null;
            Node tuple;
            boolean done = false;
            eScan = nFile.openScan();
            boolean done1 = false;
            boolean done2 = false;
            Edge e = new Edge();
            while (!done){
                tuple = eScan.getNext(nid);
                if(tuple == null) {
                    done = true;
                }
                else{
			//System.out.println(tuple.getLabel()+" "+nid.slotNo);
                        
                    //mapping of nodelabel to its correspoinding NID
                    if(tuple.getLabel().equals(sourceLabel)){
			//System.out.println(tuple.getLabel()+" "+nid.slotNo);
                        e.setSource(nid);
                        done1 = true;
                    }
                    if(tuple.getLabel().equals(destinationLabel)) {
                        e.setDestination(nid);
                        done2 = true;
                    }
                    if(done1 == true && done2 == true){
                        done = true;
                        break;
                    }
                    }
                }
            //eFile.closescan();
            e.setWeight(edgeWeight);
            e.setLabel(edgeLabel);
            byte[] tmp = e.getEdgeByteArray();
            eFile.insertEdge(tmp);
		line = br.readLine();	 
            }
	System.out.println("Node Count : "+graph.getNodeCnt()+ "\nEdge Count : "+graph.getEdgeCnt()+ "\nPage reads : "+PCounter.rcounter+
                "\nWrite Count : "+PCounter.wcounter);	
	        graph.closeDB();
        }
        catch (Exception ex){
        	ex.printStackTrace();
        }
       /* System.out.println("Node Count : "+getNodeCnt()+ "\nEdge Count : "+getEdgeCnt()+ "\nSource Count : "+getSourceCnt()+
                    "\nDestination Count : "+getDestCnt()+ "\nLabel Count : "+getLabelCnt()+ "\nPage reads : "+pcounter.rcounter+
                    "\nWrite Count : "+pcounter.wcounter);*/
     }
}
