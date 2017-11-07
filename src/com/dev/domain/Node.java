package com.dev.domain;

import java.util.List;

public interface Node<T> {
		
	public List<T> getData();
	
	public Node getParent();
	
}
