import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * Implementation of Btree (B+ Tree). They are widely used in database indexing.
 * 
 * @author devanshu
 */

public class Btree implements Tree {

	private Node root;
	private int order;

	private Btree() {

	}

	/**
	 * Method to initialize the B+Tree which takes order of the Btree as input
	 * and returns an instance of new Btree
	 * 
	 * @param order
	 * @return {@link Btree}
	 * @throws Exception
	 */
	public static Btree initialize(int order) throws Exception {
		if (order < 3) {
			throw new InvalidOrderException(
					"Order must be greater than equal to 3");
		}
		Btree btree = new Btree();
		btree.root = new DataNode();
		btree.order = order;
		return btree;
	}

	/**
	 * Method to insert a (key,value) pair into the Btree
	 * 
	 */
	public void insert(double key, String value) {

		Pair pair = new Pair(key, value);
		if (root instanceof DataNode) {
			@SuppressWarnings("unchecked")
			List<Pair> pairs = (List<Pair>) root.getData();
			add(pairs, pair);
			if (pairs.size() == order) {
				split(root);
			}
		} else {
			DataNode leaf = findLeaf(key, root);
			List<Pair> pairs = leaf.getData();
			add(pairs, pair);
			if (pairs.size() == order) {
				split(leaf);
			}
		}

	}

	/**
	 * Method to add pair in the leaf node of the BTree (B+Tree)
	 * 
	 * @param pairs
	 * @param pair
	 */
	private void add(List<Pair> pairs, Pair pair) {
		if (pairs.size() == 0) {
			pairs.add(pair);
			return;
		}

		for (int i = 0; i < pairs.size(); i++) {
			if (pairs.get(i).getKey() == pair.getKey()) {
				pairs.get(i).getValue().addAll(pair.getValue());
				return;
			} else if (pairs.get(i).getKey() > pair.getKey()) {
				pairs.add(i, pair);
				return;
			}

		}

		pairs.add(pair);

	}

	/**
	 * Method to find the appropiate leaf node to insert the given key
	 * 
	 * @param key
	 * @return {@link DataNode}
	 */
	private DataNode findLeaf(double key, Node node) {
		if (CommonUtil.isNull(node)) {
			return (DataNode) node;
		}
		if (node instanceof DataNode) {
			return (DataNode) node;
		}

		@SuppressWarnings("unchecked")
		List<Pointer> pointerList = (List<Pointer>) node.getData();
		int size = pointerList.size();
		if (key < pointerList.get(0).getKey()) {
			return findLeaf(key, pointerList.get(0).getLeft());
		}
		if (key >= pointerList.get(size - 1).getKey()) {
			return findLeaf(key, pointerList.get(size - 1).getRight());
		}

		int start = 0;
		int end = size;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			// System.out.println(start + " " + mid + " " + end);
			if (mid == start || mid == end) {
				break;
			}
			if (pointerList.get(mid).getKey() > key) {
				end = mid;
			} else {
				start = mid;
			}

		}
		return findLeaf(key, pointerList.get(mid).getRight());

		// return null;
	}

	/**
	 * Method to split the a node which has exceeded its capacity (i.e. more
	 * than order - 1 elements)
	 * 
	 * @param old
	 * @return
	 */
	private void split(Node old) {
		int size = old.getData().size();
		if (old instanceof DataNode) {
			splitDataNode(old, size);
		} else {
			splitPointerNode(old, size);
		}
	}

	/**
	 * Method to split non-leaf Nodes that have reached capacity (i.e. more than
	 * order - 1 elements)
	 * 
	 * @param old
	 * @param size
	 */
	private void splitPointerNode(Node old, int size) {
		PointerNode parent = (PointerNode) old.getParent();
		if (CommonUtil.isNull(parent)) {
			parent = new PointerNode();
			root = parent;
		}

		PointerNode temp = new PointerNode(parent);
		PointerNode nu = new PointerNode(parent);
		old.getData();
		// old Node
		@SuppressWarnings("unchecked")
		List<Pointer> leftList = new ArrayList<>((List<Pointer>) old.getData()
				.subList(0, size / 2));
		temp.setData(leftList);

		// Middle Node
		Pointer middle = (Pointer) old.getData().get(size / 2);

		// New Node
		@SuppressWarnings("unchecked")
		List<Pointer> rightList = new ArrayList<>((List<Pointer>) old.getData()
				.subList(size / 2 + 1, size));
		nu.setData(rightList);
		for (Pointer p : rightList) {
			p.getLeft().setParent(nu);
			p.getRight().setParent(nu);
		}

		old = temp;
		middle.setLeft(old);
		middle.setRight(nu);
		for (Pointer p : leftList) {
			p.getLeft().setParent(old);
			p.getRight().setParent(old);
		}

		// Adding middle to parent Node
		List<Pointer> data = parent.getData();
		add(data, middle);

		if (data.size() == order) {
			split(parent);
		}
	}

	/**
	 * 
	 * Method to split leaf Nodes that have reached capacity ( i.e more than
	 * order - 1 elements )
	 * 
	 * @param old
	 * @param size
	 */
	private void splitDataNode(Node old, int size) {
		PointerNode parent = (PointerNode) old.getParent();
		if (CommonUtil.isNull(parent)) {
			parent = new PointerNode();
			root = parent;
		}

		DataNode temp = new DataNode(parent);
		DataNode nu = new DataNode(parent);

		// old Node
		@SuppressWarnings("unchecked")
		List<Pair> leftList = new ArrayList<>((List<Pair>) old.getData()
				.subList(0, size / 2 + 1));
		temp.setData(leftList);

		// Middle Node
		Pair middle = (Pair) old.getData().get(size / 2);
		Pointer pointer = new Pointer();

		// New Node
		@SuppressWarnings("unchecked")
		List<Pair> rightList = new ArrayList<>((List<Pair>) old.getData()
				.subList(size / 2 + 1, size));
		nu.setData(rightList);

		// prev next re-assignment
		nu.setNext(((DataNode) old).getNext());
		if (!CommonUtil.isNull(((DataNode) old).getNext())) {
			((DataNode) old).getNext().setPrev(nu);
		}
		((DataNode) temp).setNext(nu);
		nu.setPrev((DataNode) temp);

		if (!CommonUtil.isNull(((DataNode) old).getPrev())) {
			((DataNode) old).getPrev().setNext(temp);
		}
		temp.setPrev(((DataNode) old).getPrev());

		old = temp;
		pointer.setLeft(old);
		pointer.setRight(nu);
		pointer.setKey(middle.getKey());

		// Adding middle to parent Node
		List<Pointer> data = parent.getData();
		add(data, pointer);

		if (data.size() == order) {
			split(parent);
		}
	}

	/**
	 * Method to add elements in non leaf Nodes of the Btree
	 * 
	 * @param data
	 * @param pointer
	 */
	private void add(List<Pointer> data, Pointer pointer) {

		int size = data.size();

		if (size == 0) {
			data.add(pointer);
			return;
		}
		// first last element
		if (data.get(0).getKey() > pointer.getKey()) {
			data.add(0, pointer);
			data.get(1).setLeft(data.get(0).getRight());
			return;
		}
		if (data.get(size - 1).getKey() < pointer.getKey()) {
			data.add(size, pointer);
			data.get(size - 1).setRight(data.get(size).getLeft());
			return;
		}
		int i = 0;
		for (i = 0; i < size - 1; i++) {
			if (data.get(i).getKey() < pointer.getKey()
					&& data.get(i + 1).getKey() > pointer.getKey()) {

				// mid elements
				data.add(i + 1, pointer);
				data.get(i).setRight(data.get(i + 1).getLeft());
				data.get(i + 2).setLeft(data.get(i + 1).getRight());
				break;
			}
		}

	}

	/**
	 * Methdd to search for a particular key in the Btree
	 * 
	 */
	public List<String> search(double key) {

		DataNode dataNode = findLeaf(key, root);
		List<Pair> pairs = dataNode.getData();
		int start = 0;
		int end = pairs.size() - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (pairs.get(mid).getKey() == key) {
				return pairs.get(mid).getValue();
			} else if (pairs.get(mid).getKey() < key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}

		}
		return new ArrayList<>();
	}

	/**
	 * Method to search elements of the Btree in a given range defined by the
	 * two input keys
	 * 
	 */
	public List<String> search(double key1, double key2) {

		List<String> output = new ArrayList<>();
		DataNode start = findLeaf(key1, root);
		DataNode end = findLeaf(key2, root);
		DataNode temp = start;
		while (!CommonUtil.isNull(temp) && temp != end.getNext()) {
			List<Pair> data = temp.getData();
			for (Pair pair : data) {
				if (pair.getKey() >= key1 && pair.getKey() <= key2) {
					for (String value : pair.getValue()) {
						String str = "(" + pair.getKey() + "," + value + ")";
						output.add(str);
					}
				}
			}
			temp = temp.getNext();
		}
		return output;
	}

}
