package com.dev;

import com.dev.domain.Btree;

public class Executor {

	public static void main(String args[]) {

		Btree btree = Btree.initialize(7);
		btree.insert(1, "one");
		btree.insert(2, "two");
		btree.insert(3, "three");
		btree.insert(4, "four");
		btree.insert(5, "five");
		btree.insert(6, "six");
		btree.insert(7, "seven");

		System.out.println(btree.search(5));
		System.out.println(btree.search(-3,4));

	}
}
