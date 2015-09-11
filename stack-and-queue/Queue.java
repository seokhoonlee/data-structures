public class Queue<T> {
	public static class Node<T> {
		public T value;
		public Node<T> next;

		public Node(T value){
			this.value = value;
			this.next = null;
		}
	}

	public Node<T> start;
	public Node<T> end;
	public int length;

	public Queue() {
		start = null;
		end = null;
		length = 0;
	}

	/**  
	 * Add input node to top of the stack.
	 * @param input value.
	 */
	public void enqueue(T inputValue) {
		Node<T> inputNode = new Node<T>(inputValue);
		
		if (start == null) {
			start = inputNode;
			end = inputNode;
			
		} else {
			end.next = inputNode;
			end = inputNode;
		}
		
		length++;
	}

	/**  
	 * Clear all the contents in the list.
	 */
	public void clear() {
		start = null;
		end = null;
		length = 0;
	}

	/**  
	 * Remove node at the top and returns the value.
	 * @return value of the top node.
	 */
	public T dequeue() {
		if (start == null) {
			return null;
		}

		T temp = start.value;

		if (start.next != null) {
			start = start.next;
		} else {
			start = null;
		}

		length--;

		return temp;
	}

	/**  
	 * Print stack in order from start to end.
	 */
	public void print() {
		Node<T> curr = start;
		
		while (curr != null) {
			System.out.print(curr.value);
			if (curr.next != null) {
				System.out.print(" -> ");
			}
			curr = curr.next;
		}
		
		System.out.print("\n");
	}

	public static void main(String[] args){
		Queue<String> queue = new Queue<String>();

		queue.enqueue("1");
		queue.enqueue("2");
		queue.enqueue("3");
		queue.print();
		queue.clear();
		queue.enqueue("3");
		queue.enqueue("4");
		queue.enqueue("5");
		queue.enqueue("6");
		queue.enqueue("7");
		queue.print();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.print();

		/**
		 * Output:
		 * 1 -> 2 -> 3
		 * 3 -> 4 -> 5 -> 6 -> 7
		 * 6 -> 7
		 */
	}
}