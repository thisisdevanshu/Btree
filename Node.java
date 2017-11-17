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

	public List<? extends NodeElement> getData();

	public Node getParent();

	public void setParent(Node node);

}
