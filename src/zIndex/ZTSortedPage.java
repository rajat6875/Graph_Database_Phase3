package zIndex;

import btree.BTSortedPage;
import btree.ConstructPageException;
import diskmgr.Page;
import global.PageId;

public class ZTSortedPage extends BTSortedPage {

	/** pin the page with pageno, and get the corresponding SortedPage
	   *@param pageno input parameter. To specify which page number the
	   *  ZTSortedPage will correspond to.
	   *@param keyType input parameter. It specifies the type of key. It can be 
	   *               AttrType.attrString or AttrType.attrInteger. 
	   *@exception  ConstructPageException  error for ZTSortedPage constructor
	   */
	  public ZTSortedPage(PageId pageno, int keyType) 
	    throws ConstructPageException 
	    { 
	      super(pageno, keyType);
	    }
	  
	  /**associate the SortedPage instance with the Page instance 
	   *@param page input parameter. To specify which page  the
	   *  ZTSortedPage will correspond to.
	   *@param keyType input parameter. It specifies the type of key. It can be 
	   *               AttrType.attrString or AttrType.attrInteger. 
	   */
	  public ZTSortedPage(Page page, int keyType) {
	    
	    super(page, keyType);	       
	  }  
	  
	  
	  /**new a page, and associate the SortedPage instance with the Page instance
	   *@param keyType input parameter. It specifies the type of key. It can be 
	   *               AttrType.attrString or AttrType.attrInteger. 
	   *@exception  ConstructPageException error for ZTSortedPage constructor
	   */ 
	  public ZTSortedPage(int keyType) 
	    throws ConstructPageException
	    {
		  super(keyType);
	    }  
	  
}
