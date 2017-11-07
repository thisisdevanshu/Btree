package com.dev.domain;

import java.util.List;

public interface Tree {

	void insert(double key, String value);

	List<String> search(double key);

	List<String> search(double key1, double key2);

}
