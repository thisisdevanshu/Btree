import java.util.List;

/**
 * 
 * @author devanshu
 * 
 *         Tree defines the various implemetation of Tree Data Structure.
 */
public interface Tree {

	void insert(double key, String value);

	List<String> search(double key);

	List<String> search(double key1, double key2);

}
