package com.dev.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair implements Comparable<Pair> {

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

	@Override
	public int compareTo(Pair arg0) {
		// TODO Auto-generated method stub
		return this.key >= arg0.getKey() ? this.key == arg0.getKey() ? 0 : 1
				: -1;
	}

}
