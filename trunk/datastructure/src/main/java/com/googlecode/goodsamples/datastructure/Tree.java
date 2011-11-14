package com.googlecode.goodsamples.datastructure;

import java.util.Arrays;

public class Tree {
	private static final int INITIAL_CAPACITY = 16;
	private int level;
	private String[] items;

	public Tree() {
		this(INITIAL_CAPACITY);
	}

	public Tree(int capacity) {
		items = new String[capacity];		
	}

	public void add(String item) {
		int result = findNode(item, 0, 1);
		items[result] = item;
	}

	private int findNode(String item, int index, int level) {
		if (items[index] == null) {
			if (this.level < level ) {
				this.level = level;
			}
			return index;
		}
		
		if (rightLeafIndexOf(index) > (items.length - 1)) {
			items = Arrays.copyOf(items, items.length * 2);
		}

		int currentRootHash = items[index].hashCode();
		int itemHash = item.hashCode();

		if (currentRootHash > itemHash) {
			return findNode(item, 2 * index + 1, ++level);
		} else {
			return findNode(item, 2 * index + 2, ++level);
		}

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < Math.pow(2, level) - 1; i++) {
			result.append(items[i]);
			result.append(",");
		}
		return result.substring(0, result.length() - 1) + "]";
	}

	
	int level() {
		return level;
	}
	
	private int rightLeafIndexOf(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	int length() {
		return items.length;
	}
}
