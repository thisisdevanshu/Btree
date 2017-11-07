package com.dev.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Btree implements Tree {

	@SuppressWarnings("rawtypes")
	private Node root;
	private int order;

	private Btree() {

	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	public static Btree initialize(int order) {
		Btree btree = new Btree();
		btree.root = new DataNode();
		btree.order = order;
		return btree;
	}

	/**
	 * 
	 */
	public void insert(double key, String value) {

		Pair pair = new Pair(key, value);
		if (root instanceof DataNode) {
			@SuppressWarnings("unchecked")
			List<Pair> pairs = root.getData();
			pairs.add(pair);
			Collections.sort(pairs);
			if (pairs.size() == order) {
				split(root);
			}
		} else {
			DataNode leaf = findLeaf(key, root);
			List<Pair> pairs = leaf.getData();
			pairs.add(pair);
			Collections.sort(pairs);
			if (pairs.size() == order) {
				split(leaf);
			}
		}

	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private DataNode findLeaf(double key,
			@SuppressWarnings("rawtypes") Node node) {
		if (CommonUtil.isNull(node)) {
			return (DataNode) node;
		}
		if (node instanceof DataNode) {
			return (DataNode) node;
		}

		@SuppressWarnings("unchecked")
		List<Pointer> pointerList = node.getData();
		int size = pointerList.size();
		if (key < pointerList.get(0).getKey()) {
			return findLeaf(key, pointerList.get(0).getLeft());
		}
		if (key >= pointerList.get(size - 1).getKey()) {
			return findLeaf(key, pointerList.get(size - 1).getRight());
		}
		for (int i = 0; i < pointerList.size() - 1; i++) {
			if (key >= pointerList.get(i).getKey()
					&& key < pointerList.get(i + 1).getKey()) {
				return findLeaf(key, pointerList.get(i).getRight());
			}

		}
		return null;
	}

	/**
	 * 
	 * @param old
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private void split(Node old) {

		int size = old.getData().size();

		if (old instanceof DataNode) {

			PointerNode parent = (PointerNode) old.getParent();
			if (CommonUtil.isNull(parent)) {
				parent = new PointerNode();
				root = parent;
			}

			DataNode temp = new DataNode(parent);
			DataNode nu = new DataNode(parent);

			// old Node
			@SuppressWarnings("unchecked")
			List<Pair> leftList = new ArrayList<>(old.getData().subList(0,
					size / 2 + 1));
			temp.setData(leftList);

			// Middle Node
			Pair middle = (Pair) old.getData().get(size / 2);
			Pointer pointer = new Pointer();

			// New Node
			@SuppressWarnings("unchecked")
			List<Pair> rightList = new ArrayList<>(old.getData().subList(
					size / 2 + 1, size));
			nu.setData(rightList);

			old = temp;
			pointer.setLeft(old);
			pointer.setRight(nu);
			pointer.setKey(middle.getKey());

			// prev next re-assignment
			nu.setNext(((DataNode) old).getNext());
			((DataNode) old).setNext(nu);

			((DataNode) old).getNext().setPrev(nu);
			nu.setPrev((DataNode) old);

			// Adding middle to parent Node
			List<Pointer> data = parent.getData();
			add(data, pointer);

			if (data.size() == order) {
				split(parent);
			}

		} else {

			PointerNode parent = (PointerNode) old.getParent();
			if (CommonUtil.isNull(parent)) {
				parent = new PointerNode();
				root = parent;
			}

			PointerNode temp = new PointerNode(parent);
			PointerNode nu = new PointerNode(parent);

			// old Node
			@SuppressWarnings("unchecked")
			List<Pointer> leftList = new ArrayList<>(old.getData().subList(0,
					size / 2));
			temp.setData(leftList);

			// Middle Node
			Pointer middle = (Pointer) old.getData().get(size / 2);

			// New Node
			@SuppressWarnings("unchecked")
			List<Pointer> rightList = new ArrayList<>(old.getData().subList(
					size / 2 + 1, size));
			nu.setData(rightList);

			old = temp;
			middle.setLeft(old);
			middle.setRight(nu);

			// Adding middle to parent Node
			List<Pointer> data = parent.getData();
			add(data, middle);

			if (data.size() == order) {
				split(parent);
			}
		}

	}

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
				break;
			}
		}

		// mid elements
		data.add(i + 1, pointer);
		data.get(i).setRight(data.get(i + 1).getLeft());
		data.get(i + 2).setLeft(data.get(i + 1).getRight());
	}

	/**
	 * 
	 */
	public String search(double key) {

		DataNode dataNode = findLeaf(key, root);
		for (Pair pair : dataNode.getData()) {
			if (pair.getKey() == key) {
				return pair.getValue();
			}
		}
		return null;
	}

	/**
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
					output.add(pair.getValue());
				}
			}
			temp = temp.getNext();
		}
		return output;
	}

}
