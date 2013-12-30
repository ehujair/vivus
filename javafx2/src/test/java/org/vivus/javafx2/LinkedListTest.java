package org.vivus.javafx2;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testPushPop() {
		LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5,6));
		System.out.println("before: " + list.size());
		System.out.println("opo: " + list.pop());
		System.out.println("opo: " + list.pop());
		System.out.println("opo: " + list.pop());
		System.out.println("after: " + list.size());
	}
}
