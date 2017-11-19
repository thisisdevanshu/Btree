import java.util.List;

/**
 * 
 * 
 * Tree defines the various implementations of Tree Data Structure.
 * 
 * @author devanshu
 * 
 */
public interface Tree {

	/**
	 * Method insert (key,value) in the Tree
	 * 
	 * @param key
	 * @param value
	 */
	void insert(double key, String value);

	/**
	 * Method to search value for a single key in the tree.
	 * 
	 * @param key
	 * @return {@link List of String}
	 */
	List<String> search(double key);

	/**
	 * Method for range search between key1 and key2.
	 * 
	 * @param key1
	 * @param key2
	 * @return {@link List of String}
	 */
	List<String> search(double key1, double key2);

}
