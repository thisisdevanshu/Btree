package com.dev.domain;

import java.util.ArrayList;
import java.util.List;

public class DataNode implements Node<Pair> {

	private List<Pair> data = new ArrayList<>();
	private Node<Pointer> parent = null;
	private DataNode next = null;
	private DataNode prev = null;

	public DataNode() {

	}

	public DataNode(@SuppressWarnings("rawtypes") Node parent) {
		this.parent = parent;
	}

	public List<Pair> getData() {
		return data;
	}

	public Node<Pointer> getParent() {
		return parent;
	}

	public void setParent(Node<Pointer> parent) {
		this.parent = parent;
	}

	public void setData(List<Pair> data) {
		this.data = data;
	}

	public DataNode getNext() {
		return next;
	}

	public void setNext(DataNode next) {
		this.next = next;
	}

	public DataNode getPrev() {
		return prev;
	}

	public void setPrev(DataNode prev) {
		this.prev = prev;
	}

}
