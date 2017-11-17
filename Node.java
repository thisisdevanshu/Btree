import java.util.List;

/**
 * 
 * @author devanshu
 * 
 *         Node interface defines a structure for the various types of nodes of
 *         a BTree.
 * 
 */
public interface Node {

	public List<? extends NodeElement> getData();

	public Node getParent();

	public void setParent(Node node);

}
