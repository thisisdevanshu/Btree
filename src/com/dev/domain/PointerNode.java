package com.dev.domain;

import java.util.ArrayList;
import java.util.List;

public class PointerNode implements Node<Pointer> {

	private List<Pointer> data = new ArrayList<>();
	private Node<Pointer> parent;

	public PointerNode() {

	}

	@SuppressWarnings("unchecked")
	public PointerNode(@SuppressWarnings("rawtypes") Node parent) {
		this.parent = parent;
	}

	public List<Pointer> getData() {
		return data;
	}

	public Node<Pointer> getParent() {
		return parent;
	}

	@SuppressWarnings("unchecked")
	public void setParent(@SuppressWarnings("rawtypes") Node parent) {
		this.parent = parent;
	}

	public void setData(List<Pointer> data) {
		this.data = data;
	}

}
