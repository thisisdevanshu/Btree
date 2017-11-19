import java.util.List;

/**
 * 
 * 
 * 
 * Node interface defines the types of nodes of a BTree.
 * 
 * @author devanshu
 * 
 */
public interface Node {

	/**
	 * Method to get data stored on the node.
	 * 
	 * @return {@link List of NodeElement}
	 */
	public List<? extends NodeElement> getData();

	/**
	 * 
	 * Method to access the parent of the current Node
	 * 
	 * @return {@link Node}
	 */
	public Node getParent();

	/**
	 * Method to set Parent node of the given node
	 * 
	 * @param node
	 */
	public void setParent(Node node);

}
