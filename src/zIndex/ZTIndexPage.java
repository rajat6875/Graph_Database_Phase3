package zIndex;

import java.io.IOException;

import btree.BTIndexPage;
import btree.ConstructPageException;
import btree.NodeType;
import diskmgr.Page;
import global.PageId;

public class ZTIndexPage extends BTIndexPage {

	public ZTIndexPage(PageId pageno, int keyType) throws IOException, ConstructPageException {
		super(pageno, keyType);
		setType(NodeType.INDEX);
	}

	/**
	 * associate the ZTIndexPage instance with the Page instance, also it sets
	 * the type of node to be NodeType.INDEX.
	 * 
	 * @param page
	 *            input parameter. To specify which page the BTIndexPage will
	 *            correspond to.
	 * @param keyType
	 *            either AttrType.attrInteger or AttrType.attrString. Input
	 *            parameter.
	 * @exception IOException
	 *                error from the lower layer
	 * @exception ConstructPageException
	 *                error when BTIndexpage constructor
	 */
	public ZTIndexPage(Page page, int keyType) throws IOException, ConstructPageException {
		super(page, keyType);
		setType(NodeType.INDEX);
	}

	/*
	 * new a page, associate the ZTIndexPage instance with the Page instance,
	 * also it sets the type of node to be NodeType.INDEX.
	 * 
	 * @param keyType either AttrType.attrInteger or AttrType.attrString. Input
	 * parameter.
	 * 
	 * @exception IOException error from the lower layer
	 * 
	 * @exception ConstructPageException error when BTIndexpage constructor
	 */
	public ZTIndexPage(int keyType) throws IOException, ConstructPageException {
		super(keyType);
		setType(NodeType.INDEX);
	}

}
