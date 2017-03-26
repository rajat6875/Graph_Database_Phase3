package testsGraph;

import java.io.*;
import global.*;
import diskmgr.*;
import nodeheap.*;

public class BatchNodeInsert{

    public static void main(String [] ar) throws Exception{
        String nodeFileName = ar[1];
        String graphDBName = ar[2];
        graphDB graph = new graphDB();
        if(!graph.graphDBExists(graphDBName)){
        	graph.openDB(graphDBName);
        }
        
        NodeHeapFile nFile = new NodeHeapFile(graph.nodeFileName);
       // public BTreeFile bFile;

        FileReader fr = new FileReader(nodeFileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line!=null){
           // System.out.println(line);
            String nodeData[] = line.split(" ");
            String nodeLabel = nodeData[0];
            Node n = new Node();
            n.setLabel(nodeLabel);
            Descriptor d = new Descriptor();
            d.set(Integer.parseInt(nodeData[1]), Integer.parseInt(nodeData[2]), Integer.parseInt(nodeData[3]), Integer.parseInt(nodeData[4]),Integer.parseInt( nodeData[5]));
            n.setDesc(d);
            byte[] tmp = n.getNodeByteArray();
            nFile.insertRecord(tmp);
	    line = br.readLine();
       
            //String key = new StringKey(nodeLabel);
           // bFile.insert(key, nid);
        }
	int nodes=graph.getNodeCnt();
	System.out.println(nodes);
	System.out.println("Node Count : "+graph.getNodeCnt()+ "\nEdge Count : "+graph.getEdgeCnt()+ "\nPage reads : "+PCounter.rcounter+
                "\nWrite Count : "+PCounter.wcounter);

	graph.closeDB();
      /*  System.out.println("Node Count : "+graphDB.getNodeCnt()+ "\nEdge Count : "+getEdgeCnt()+ "\nSource Count : "+getSourceCnt()+
        "\nDestination Count : "+getDestCnt()+ "\nLabel Count : "+getLabelCnt()+ "\nPage reads : "+pcounter.rcounter+
                "\nWrite Count : "+pcounter.wcounter);
	*/
    }
}



