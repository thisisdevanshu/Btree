package com.dev.domain;

import java.util.List;

public interface Node<T> {
		
	public List<T> getData();
	
	@SuppressWarnings("rawtypes")
	public Node getParent();
	
}
