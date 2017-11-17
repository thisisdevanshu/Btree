
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author devanshu
 * 
 *         Pair are the elements that are saved at the leaf Nodes of the Btree.
 *         They are of the form (key,value).
 * 
 */
public class Pair implements NodeElement {

	private double key;
	private List<String> value = new ArrayList<>();

	public Pair(double key, String value) {
		this.key = key;
		this.value.add(value);
	}

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

}
