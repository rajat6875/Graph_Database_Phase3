package zIndex;

import java.io.IOException;

import btree.AddFileEntryException;
import btree.BTreeFile;
import btree.ConstructPageException;
import btree.GetFileEntryException;
import btree.NodeType;
import btree.PinPageException;
import global.PageId;

public class ZTreeFile extends BTreeFile {

	public ZTreeFile(String filename) throws GetFileEntryException, PinPageException, ConstructPageException {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	/**
	 * if index file exists, open it; else create it.
	 * 
	 * @param filename
	 *            file name. Input parameter.
	 * @param keytype
	 *            the type of key. Input parameter.
	 * @param keysize
	 *            the maximum size of a key. Input parameter.
	 * @param delete_fashion
	 *            full delete or naive delete. Input parameter. It is either
	 *            DeleteFashion.NAIVE_DELETE or DeleteFashion.FULL_DELETE.
	 * @exception GetFileEntryException
	 *                can not get file
	 * @exception ConstructPageException
	 *                page constructor failed
	 * @exception IOException
	 *                error from lower layer
	 * @exception AddFileEntryException
	 *                can not add file into DB
	 */
	public ZTreeFile(String filename, int keytype, int keysize, int delete_fashion) throws GetFileEntryException, ConstructPageException, AddFileEntryException, IOException{

		super(filename, keytype, keysize, delete_fashion);

	}

}
