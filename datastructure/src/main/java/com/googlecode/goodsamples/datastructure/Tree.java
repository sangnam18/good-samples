package com.googlecode.goodsamples.datastructure;

import java.util.Arrays;

public class Tree<T> {
	private static final int NO_ITEM = -1;
	private static final int INITIAL_CAPACITY = 16;
	private int level;
	private transient Object[] items;

	public Tree() {
		this(INITIAL_CAPACITY);
	}

	public Tree(int capacity) {
		items = new Object[capacity];		
	}

	public void add(T item) {
		int result = findNewNodeIndexToInsert(item, 0, 1);
		items[result] = item;
	}

	@SuppressWarnings("unchecked")
	public T searchBy(T item) {
		int result = findMatchedNodeIndex(item, 0, 1);
		return result == NO_ITEM ? null : (T)items[result];
	}

	private int findMatchedNodeIndex(T item, int index, int level) {
		if (item.equals(items[index])) {
			return index;
		}
		
		doubleArrayWhenItNeeds(index);

		@SuppressWarnings("unchecked")
		T currentItem = (T) items[index];
		if (currentItem == null) {
			return NO_ITEM;
		} 
		
		int currentRootHash = currentItem.hashCode();
		int itemHash = item.hashCode();

		if (currentRootHash > itemHash) {
			return findMatchedNodeIndex(item, left(index), ++level);
		} else {
			return findMatchedNodeIndex(item, right(index), ++level);
		}
	}

	private int findNewNodeIndexToInsert(T item, int index, int level) {
		if (items[index] == null) {
			if (this.level < level ) {
				this.level = level;
			}
			return index;
		}
		
		doubleArrayWhenItNeeds(index);

		int currentRootHash = items[index].hashCode();
		int itemHash = item.hashCode();

		if (currentRootHash > itemHash) {
			return findNewNodeIndexToInsert(item, left(index), ++level);
		} else {
			return findNewNodeIndexToInsert(item, right(index), ++level);
		}
	}

	private void doubleArrayWhenItNeeds(int index) {
		if (rightLeafIndexOf(index) > maximumIndex()) {
			items = Arrays.copyOf(items, items.length * 2);
		}
	}

	private int maximumIndex() {
		return items.length - 1;
	}

	private int right(int index) {
		return 2 * index + 2;
	}

	private int left(int index) {
		return 2 * index + 1;
	}

	private int rightLeafIndexOf(int parentIndex) {
		return left(parentIndex);
	}

	int length() {
		return items.length;
	}

	int level() {
		return level;
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
}
