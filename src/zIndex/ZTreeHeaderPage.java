package zIndex;

import btree.BTreeHeaderPage;
import btree.ConstructPageException;
import diskmgr.Page;
import global.PageId;

public class ZTreeHeaderPage extends BTreeHeaderPage {

	public ZTreeHeaderPage(PageId pageno) throws ConstructPageException {
		super(pageno);
		// TODO Auto-generated constructor stub
	}

	public ZTreeHeaderPage() throws ConstructPageException {

	}

	public ZTreeHeaderPage(Page page) {

		super(page);
	}

}
