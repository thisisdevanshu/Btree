package com.dev.domain;

import java.util.ArrayList;
import java.util.List;

public class PointerNode implements Node<Pointer> {

	private List<Pointer> data = new ArrayList<>();
	private Node<Pointer> parent;

	public PointerNode() {

	}

	public PointerNode(@SuppressWarnings("rawtypes") Node parent) {
		this.parent = parent;
	}

	public List<Pointer> getData() {
		return data;
	}

	public Node<Pointer> getParent() {
		return parent;
	}

	public void setParent(Node<Pointer> parent) {
		this.parent = parent;
	}

	public void setData(List<Pointer> data) {
		this.data = data;
	}

}
