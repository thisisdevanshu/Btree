import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * DataNode are implementation of the leaf Nodes of Btree. It has a parent node.
 * The prev and next pointers form a doubly linked list of leaf nodes.
 * 
 * * @author devanshu
 * 
 */
public class DataNode implements Node {

	private List<Pair> data = new ArrayList<>();
	private Node parent = null;
	private DataNode next = null;
	private DataNode prev = null;

	public DataNode() {

	}

	public DataNode(Node parent) {
		this.parent = parent;
	}

	public List<Pair> getData() {
		return data;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setData(List<Pair> data) {
		this.data = data;
	}

	public DataNode getNext() {
		return next;
	}

	public void setNext(DataNode next) {
		this.next = next;
	}

	public DataNode getPrev() {
		return prev;
	}

	public void setPrev(DataNode prev) {
		this.prev = prev;
	}

}
