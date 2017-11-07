package com.dev.domain;

public class Pointer implements Comparable<Pointer> {

	private double key;
	@SuppressWarnings("rawtypes")
	private Node left;
	@SuppressWarnings("rawtypes")
	private Node right;

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
	}

	@SuppressWarnings("rawtypes")
	public Node getLeft() {
		return left;
	}

	public void setLeft(@SuppressWarnings("rawtypes") Node left) {
		this.left = left;
	}

	@SuppressWarnings("rawtypes")
	public Node getRight() {
		return right;
	}

	public void setRight(@SuppressWarnings("rawtypes") Node right) {
		this.right = right;
	}

	@Override
	public int compareTo(Pointer arg0) {
		// TODO Auto-generated method stub
		return this.key >= arg0.getKey() ? this.key == arg0.getKey() ? 0 : 1
				: -1;
	}

}
