package com.dev.domain;

public class Pair implements Comparable<Pair> {

	private double key;
	private String value;

	public Pair(double key, String value) {
		this.key = key;
		this.value = value;
	}

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(Pair arg0) {
		// TODO Auto-generated method stub
		return this.key >= arg0.getKey() ? this.key == arg0.getKey() ? 0 : 1
				: -1;
	}

}
