package com.revature.test;

import java.util.List;

public class Class1 {

	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;
		private int upperBound = 0;
		private int lowerBound = 0;
		private int size = 0;
		public int indexOf(T t) {
			// TODO Write an implementation for this method declaration
			if(sortedList.size() % 2 == 1) {
				if((sortedList.get((size-1)/2 + lowerBound)).compareTo(t) == 0)
					return (size-1)/2 + lowerBound;
				if((sortedList.get((size-1)/2 + lowerBound).compareTo(t) < 0)) {
					this.lowerBound = (size-1)/2+lowerBound+1;
					this.size = upperBound-lowerBound;
					return indexOf(t);
				}
				if(sortedList.get((size-1)/2 + lowerBound).compareTo(t) > 0) {
					this.upperBound = (size-1)/2+lowerBound-1;
					this.size = upperBound-lowerBound;
					return indexOf(t);
				}
			}
			else {
				if((sortedList.get((size)/2 + lowerBound)).compareTo(t) == 0)
					return (size)/2 + lowerBound;
				if((sortedList.get((size)/2 + lowerBound).compareTo(t) < 0)) {
					this.lowerBound = (size)/2+lowerBound+1;
					this.size = upperBound-lowerBound;
					return indexOf(t);
				}
				if(sortedList.get((size)/2 + lowerBound).compareTo(t) > 0) {
					this.upperBound = (size)/2+lowerBound-1;
					this.size = upperBound-lowerBound;
					return indexOf(t);
				}
			}
				//else if ((sortedList.(sortedList.size()/2).compareTo(7)))
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
			this.upperBound = sortedList.size();
			this.size = upperBound - lowerBound;

			
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
			this.upperBound = sortedList.size();
			this.size = upperBound - lowerBound;

		}
	}
}
