/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and Tamassia
*
* This implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeLow is called.
*
* This implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
@SuppressWarnings({"rawtypes", "unchecked"})
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected Comparable[] storage;
	protected int currentSize;

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		storage = new Comparable[DEFAULT_SIZE];
		currentSize = 0;
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		storage = new Comparable[size]; 
	}
	
	public int leftChild(int i) {
		return ((i*2)+1);
	}
	
	public int rightChild(int i) {
		return ((i*2)+2);
	}
	
	public int parent(int i) {
		return ((i-1)/2);
	}
	
	public boolean isEmpty() {
		return currentSize==0;
	}
	
	public int size() {
		return currentSize;
	}
	
	public void swap(int index, int otherIndex) {
		Comparable temp = storage[index];
		storage[index] = storage[otherIndex];
		storage[otherIndex] = temp;
	}
	
	public int minChild(int index) {
		int rChild = rightChild(index);
		int min = leftChild(index);
		if (min == currentSize-1) {
			return min;
		} else if (storage[min].compareTo(storage[rChild]) > 0) {
			min = rChild;
		} 
		return min;
	}
	
	public boolean isLeaf(int index) {
		if (currentSize == 1) {
			return true;
		} else if ((rightChild(index) >= currentSize) || (leftChild(index) >= currentSize)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public Comparable removeMin() throws HeapEmptyException {
		if (isEmpty()) {
			throw new HeapEmptyException();
		} 
		Comparable min = storage[0];
		storage[0] = storage[currentSize-1];
		bubbleDown(0);
		currentSize--;
		return min;
	}
	
	public void insert(Comparable element) throws HeapFullException {
		if (isFull()) {
			throw new HeapFullException();
		} else {
			storage[currentSize] = element;
			bubbleUp(currentSize);
			currentSize++;
		}	
    }
	
	public void bubbleUp(int index) {
		if (index == 0) { 
			return;
		} else if (storage[parent(index)].compareTo(storage[index]) < 0) {
			return;
		} else {
			swap(index, parent(index));
			bubbleUp(parent(index));
		}
	}
	
	private void bubbleDown(int index) {
		if (isLeaf(index)) {
			return;
		} 
		int minChild = minChild(index);
		if (storage[index].compareTo(storage[minChild]) > 0) {
			swap(index, minChild);
			bubbleDown(minChild);
		}
	}
	
	
	public boolean isFull() {
		if (storage.length > DEFAULT_SIZE) {
			if (currentSize == storage.length) {
				return true;
			} else {
				return false;
			}
		}
		
		if (currentSize == storage.length) {
			return true;
		} else if (currentSize == DEFAULT_SIZE) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public String toString() {
		String s = "";
		String sep = "";
		// This implementation of toString assumes you 
		// are using a 1-based approach. Update the initial
		// and final value for i if using a 0-based
		for(int i=0; i<=currentSize-1; i++) {
			s += sep + storage[i];
			sep = " ";
		}
		return s;
	}
}
