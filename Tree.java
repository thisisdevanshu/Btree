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

	void insert(double key, String value);

	List<String> search(double key);

	List<String> search(double key1, double key2);

}
