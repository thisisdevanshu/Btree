package com.dev;

import com.dev.domain.Btree;

public class Executor {

	public static void main(String args[]) throws Exception {

		Btree btree = Btree.initialize(3);
		btree.insert(7, "seven");
		btree.insert(8, "eight");
		btree.insert(9, "nine");
		btree.insert(10, "ten");
		btree.insert(4, "four");
		btree.insert(5, "five");
		btree.insert(15, "fifteen");
		btree.insert(6, "six");
		btree.insert(6, "seven");
		btree.insert(1, "one");
		btree.insert(2, "two");
		btree.insert(3, "three");
		btree.insert(11, "eleven");
		btree.insert(12, "twelve");
		btree.insert(13, "thirteen");

		System.out.println(btree.search(1));
		System.out.println(btree.search(3,10));

	}
}
