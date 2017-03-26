package edgeheap;

import java.io.IOException;
import global.*;
import global.AttrType;
import global.EID;
import global.NID;
import heap.FieldNumberOutOfBoundException;
import heap.Tuple;
import nodeheap.*;
import diskmgr.*;
//∗ Edge(): Class constructor creates a new node with the appropriate size.
//∗ Edge(byte[] aedge, int offset): Construct a node from a byte array.
//∗ Edge(Edge fromEdge): Construct an edge from another edge through copy.
//∗ attrString getLabel(): Returns the label.
//∗ attrInteger getWeight(): Returns the weight.
//∗ NID getSource(): Returns the node ID of the source node.
//∗ NID getDestination(): Returns the node ID of the destination node.
//∗ Edge setLabel(attrString Label): Set the label.
//∗ Edge setWeight(attrInteger Weight): Set the weight.
//∗ Edge setSource(NID sourceID): Set the source.
//∗ Edge setDestination(NID destID ): Set the destination.
//∗ byte[] getNodeByteArray(): Copy the edge to byte array out.
//∗ void print(): Print out the edge.
//∗ size(): Get the length of the edge
//∗ edgeCopy(Edge fromEdge): Copy the given edge
//∗ edgeInit(byte[] aedge, int offset): This is used when you don’t want to use the constructor
//∗ edgeSet(byte[] fromedge, int offset): Set an edge with the given byte array and offset.


public class Edge extends Tuple {
	
	private NID dest, src;
	private String label;
	private Integer weight;
	
	
	public Edge(){
		AttrType [] NTypes = {
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrString),
	};
	short[] Nsize = new short[1];      
        Nsize[0] = 10;
	try{
		super.setHdr((short) 6, NTypes, Nsize);
	} 
	catch (Exception e){
		e.printStackTrace();	
	}
	
	}
	
	public Edge(byte[] aedge, int offset){
		AttrType [] NTypes = {
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrInteger),
		new AttrType(AttrType.attrString),
	};
	short[] Nsize = new short[1];      
        Nsize[0] = 10;
	try{
		super.setHdr((short) 6, NTypes, Nsize);
	} 
	catch (Exception e){
		e.printStackTrace();	
	}
	super.tupleSet(aedge, offset, aedge.length);
	}
	
	public Edge(Edge fromEdge){	
			
		super.tupleSet(fromEdge.getTupleByteArray(), fromEdge.getOffset(), fromEdge.size());
	}
	
	//∗ attrString getLabel(): Returns the label.
	public String getLabel(){
		String lbl=null;
		try {
			lbl=super.getStrFld(6);
		} catch (FieldNumberOutOfBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lbl;
	}
	//∗ attrInteger getWeight(): Returns the weight.
	public Integer getWeight(){
		Integer wt = null;
		try {
			 wt=super.getIntFld(5);
		} catch (FieldNumberOutOfBoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wt;
	}
	//∗ NID getSource(): Returns the node ID of the source node.
	public NID getSource() throws FieldNumberOutOfBoundException, IOException{
		NID nid= new NID();
		nid.pageNo.pid = super.getIntFld(1);
		nid.slotNo = super.getIntFld(2);
		return nid;
	}
	//∗ NID getDestination(): Returns the node ID of the destination node.
	public NID getDestination() throws FieldNumberOutOfBoundException, IOException{
		
		PageId pno= new PageId();
		pno.pid = super.getIntFld(3);
		
		NID nid= new NID(pno,super.getIntFld(4));		
		return nid;
		}
	
	//∗ Edge setLabel(attrString Label): Set the label.
	public void setLabel(String label) throws FieldNumberOutOfBoundException, IOException{
		super.setStrFld(6,label);
		this.label=label;
	}
	//∗ Edge setWeight(attrInteger Weight): Set the weight.
	public void setWeight(Integer weight) throws FieldNumberOutOfBoundException, IOException{
		super.setIntFld(5,weight);
		this.weight=weight;
	}
	
	//∗ Edge setSource(NID sourceID): Set the source.
	public void setSource(NID sourceID) throws FieldNumberOutOfBoundException, IOException{
		src=new NID();
		super.setIntFld(1,sourceID.pageNo.pid);
		super.setIntFld(2,sourceID.slotNo);
		this.src.pageNo.pid=sourceID.pageNo.pid;
		this.src.slotNo=sourceID.slotNo;
	}
	//∗ Edge setDestination(NID destID ): Set the destination.
	public void setDestination(NID destID ) throws FieldNumberOutOfBoundException, IOException{
		dest= new NID();
		super.setIntFld(3,destID.pageNo.pid);
		super.setIntFld(4,destID.slotNo);
		this.dest.pageNo.pid=destID.pageNo.pid;
		this.dest.slotNo=destID.slotNo;
	}
	//∗ byte[] getNodeByteArray(): Copy the edge to byte array out.
	public byte[] getEdgeByteArray(){
		return super.getTupleByteArray();
	}
	//∗ void print(): Print out the edge.
	public void print() throws Exception{
		
		/*AttrType [] NTypes = {
				new AttrType(AttrType.attrInteger),
				new AttrType(AttrType.attrInteger),
				new AttrType(AttrType.attrInteger),
				new AttrType(AttrType.attrInteger),
				new AttrType(AttrType.attrInteger),
				new AttrType(AttrType.attrString),
			};
		super.print(NTypes);*/
		NID nid1 = this.getDestination();
		NID nid2= this.getSource();
		NodeHeapFile nfile = new NodeHeapFile(graphDB.nodeFileName);
		Node node1 = nfile.getRecord(nid1);
		Node node2 = nfile.getRecord(nid2);
		System.out.println(node2.getLabel()+","+node1.getLabel()+","+ this.getWeight()+", "+this.getLabel());
	}
	//∗ size(): Get the length of the edge
	//Not sure if need to return edge weight or just some size...for now returning just 1 since one edge is between two nodes.
	public short size(){
		return super.size();
	}
	//∗ edgeCopy(Edge fromEdge): Copy the given edge
	public void edgeCopy(Edge fromEdge){
		super.tupleSet(fromEdge.getTupleByteArray(), fromEdge.getOffset(), fromEdge.size());
	}
	//∗ edgeInit(byte[] aedge, int offset): This is used when you don’t want to use the constructor
	 public void edgeInit(byte[] aedge, int offset){
		 super.tupleInit(aedge, offset, aedge.length);
	 }
	//∗ edgeSet(byte[] fromedge, int offset): Set an edge with the given byte array and offset.
	 public void edgeSet(byte[] fromedge, int offset){
		 super.tupleSet(fromedge, offset, fromedge.length);
	 }

	
}
