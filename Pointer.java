/**
 * 
 * 
 * 
 * Pointers are the elements that are stored at the non leaf nodes of the Btree.
 * 
 * * @author devanshu
 * 
 */
public class Pointer implements NodeElement {

	private double key;
	private Node left;
	private Node right;

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

}
