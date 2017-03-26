package zIndex;

import java.io.IOException;

import btree.BTLeafPage;
import btree.ConstructPageException;
import btree.NodeType;
import diskmgr.Page;
import global.PageId;

public class ZTLeafPage extends BTLeafPage {
	/**
	 * pin the page with pageno, and get the corresponding ZTLeafPage, also it
	 * sets the type to be NodeType.LEAF.
	 * 
	 * @param pageno
	 *            Input parameter. To specify which page number the ZTLeafPage
	 *            will correspond to.
	 * @param keyType
	 *            either AttrType.attrInteger or AttrType.attrString. Input
	 *            parameter.
	 * @exception IOException
	 *                error from the lower layer
	 * @exception ConstructPageException
	 *                ZTLeafPage constructor error
	 */
	public ZTLeafPage(PageId pageno, int keyType) throws IOException, ConstructPageException {
		super(pageno, keyType);

	}

	/**
	 * associate the ZTLeafPage instance with the Page instance, also it sets
	 * the type to be NodeType.LEAF.
	 * 
	 * @param page
	 *            input parameter. To specify which page the ZTLeafPage will
	 *            correspond to.
	 * @param keyType
	 *            either AttrType.attrInteger or AttrType.attrString. Input
	 *            parameter.
	 * @exception IOException
	 *                error from the lower layer
	 * @exception ConstructPageException
	 *                ZTLeafPage constructor error
	 */
	public ZTLeafPage(Page page, int keyType) throws IOException, ConstructPageException {
		super(page, keyType);

	}

	/**
	 * new a page, associate the ZTLeafPage instance with the Page instance,
	 * also it sets the type to be NodeType.LEAF.
	 * 
	 * @param keyType
	 *            either AttrType.attrInteger or AttrType.attrString. Input
	 *            parameter.
	 * @exception IOException
	 *                error from the lower layer
	 * @exception ConstructPageException
	 *                ZTLeafPage constructor error
	 */
	public ZTLeafPage(int keyType) throws IOException, ConstructPageException {
		super(keyType);
	}

}
