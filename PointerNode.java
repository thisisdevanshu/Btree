import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 
 * PointerNode is the implementation for the non leaf nodesof the Btree
 * (B+Tree).
 * 
 * @author devanshu
 * 
 */
public class PointerNode implements Node {

	private List<Pointer> data = new ArrayList<>();
	private Node parent;

	public PointerNode() {

	}

	public PointerNode(Node parent) {
		this.parent = parent;
	}

	public List<Pointer> getData() {
		return data;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setData(List<Pointer> data) {
		this.data = data;
	}

}
