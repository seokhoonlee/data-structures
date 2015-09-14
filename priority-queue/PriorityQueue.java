/**
 *	All values of the node has to be different for this Priority Queue Binary Heap implementation.
 *	If you run into trouble doing so, consider coming up with an attribute key containing UID.
 *	Also note that, generic type is not supported for arrays. Therefore, string type was used for this example.
 */
public class PriorityQueue {
	public static final int LIMIT = 10000;
	public String[] array = new String[LIMIT];
	public int size = 0;

	public PriorityQueue() {
		size = 0;
	}

	/**  
	 * Add input node to the priority queue.
	 * @param input value.
	 */
	public void insert(String inputValue) {
		size++;		

		array[size] = inputValue;

		shiftUp(size);
	}

	/**  
	 * Shift up the value from given index until root.
	 * @note This is a PRIVATE function.
	 * @param Index of the value to be shifted up from.
	 */
	private void shiftUp(int index) {
		if (index <= 1) {
			return;
		}

		String parent = array[index / 2];
		String curr = array[index];

		if (parent.compareTo(curr) > 0) {
			return;
		} else {
			swapIndex(index / 2, index);
			shiftUp(index / 2);
		}
	}

	/**  
	 * Swap values of two index input.
	 * @note This is a PRIVATE function.
	 * @param integer value of first index and second index.
	 */
	private void swapIndex(int index1, int index2) {
		String temp;

		temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**  
	 * Remove largest node for max heap and smallest node for min heap.
	 * @return String value of the maximum of this heap, returns null if heap has no elements.
	 */
	public String removeMax() {
		if (size < 1) {
			return null;
		}

		String temp = array[1];

		swapIndex(1, size);
		
		size--;

		shiftDown(1);

		return temp;
	}

	/**  
	 * Shift down the value from given index til end.
	 * @note This is a PRIVATE function.
	 * @param Index of the value to be shifted down from.
	 */
	private void shiftDown(int index) {
		if (index > size) {
			return;
		}
		
		String curr = array[index];
		String left = array[index * 2];
		String right = array[index * 2 + 1];
		
		if (index * 2 > size) {
			return;
		} else if (index * 2 == size) {
			if (curr.compareTo(left) > 0) {
				return;
			} else {
				swapIndex(index * 2, index);
				shiftDown(index * 2);
			}
		} else {
			if (curr.compareTo(left) > 0 && curr.compareTo(right) > 0) {
				return;
			} else {
				if (left.compareTo(right) > 0) {
					swapIndex(index * 2, index);
					shiftDown(index * 2);
				} else {
					swapIndex(index * 2 + 1, index);
					shiftDown(index * 2 + 1);
				}
			}
		}
	}

	/**  
	 * Check if that value exists in this priority queue.
	 * @return true if exists, false if doesn't exist.
	 */
	public boolean contains(String inputValue) {
		for (int i = 1; i <= size; i++) {
			if (array[i] == inputValue) {
				return true;
			}
		}

		return false;
	}

	/**  
	 * Print heap in order from top to bottom.
	 */
	public void print() {
		int count = 1;
		int square = 1;

		System.out.print("Root\n");
		
		for (int i = 1; i <= size; i++) {
			System.out.print(array[i]);

			if (count == Math.pow(2, square) - 1) {
				System.out.print("\n");
				count++;
				square++;
			} else {
				count++;
			}
		}
		
		System.out.print("\n");
	}

	public static void main(String[] args){
		PriorityQueue heap = new PriorityQueue();

		heap.insert("A");
		heap.insert("B");
		heap.insert("C");
		heap.insert("D");
		heap.insert("E");
		heap.insert("F");
		heap.insert("G");
		heap.insert("H");
		heap.insert("I");
		heap.insert("J");
		heap.insert("K");
		heap.insert("L");
		heap.insert("M");
		heap.insert("N");
		heap.insert("O");
		heap.insert("P");
		heap.insert("Q");
		heap.insert("R");
		heap.print();
		heap.removeMax();
		heap.removeMax();
		heap.print();
		System.out.print(heap.contains("D") + "\n");
		System.out.print(heap.contains("Q"));
		
		/**
		 * Root
		 * R
		 * QN
		 * PIKM
		 * JOCHBFEL
		 * AGD
		 * Root
		 * P
		 * ON
		 * JIKM
		 * GDCHBFEL
		 * A
		 * true
		 * false
		 */
	}
}