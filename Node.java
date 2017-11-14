

import java.util.List;

public interface Node<T> {
		
	public List<T> getData();
	
	@SuppressWarnings("rawtypes")
	public Node getParent();
	
	@SuppressWarnings("rawtypes")
	public void setParent(Node node);
	
}
