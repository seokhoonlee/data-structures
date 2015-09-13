public class DoublyLinkedList<T> {
	public static class Node<T> {
		public T value;
		public Node<T> prev;
		public Node<T> next;

		public Node(T inputValue){
			this.value = inputValue;
			this.prev = null;
			this.next = null;
		}
	}
	
	public Node<T> head;
	public Node<T> tail;
	public int length;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	/**  
	 * Add input node to the list.
	 * @param input value.
	 */
	public void add(T inputValue) {
		Node<T> inputNode = new Node<T>(inputValue);
		
		if (head == null) {
			head = inputNode;
			tail = inputNode;
			
		} else {
			tail.next = inputNode;
			inputNode.prev = tail;
			tail = inputNode;
		}
		
		length++;
	}

	/**  
	 * Clear all the contents in the list.
	 */
	public void clear() {
		head = null;
		tail = null;
		length = 0;
	}
	
	/**  
	 * Get element at index.
	 * @note This is a PRIVATE function.
	 * @param index (starting from 0).
	 * @return node if exists, null if doesn't exist.
	 */
	private Node<T> getNodeAt(int index) {
		Node<T> curr = head;
		
		for (int i = 0; i < index; i++) {
			if (curr != null) {
				curr = curr.next;
			} else {
				return null;
			}
		}
		
		return curr;
	}
	
	/**  
	 * Remove node at input index from the list.
	 * @param index (starting from 0).
	 * @return true if exists, false if doesn't exist.
	 */
	public boolean removeAt(int index) {
		if (index > length || length == 0) {
			return false;
		}
		
		if (index == 0) {
			head = head.next;
			
		} else if (index == length - 1) {
			tail = tail.prev;
			tail.next = null;
			
		} else {
			Node<T> curr = getNodeAt(index - 1);
			
			curr.next = curr.next.next;
			if (curr.next != null) {
				curr.next.prev = curr;
			}
		}
		
		length--;
		return true;
	}
	
	/**  
	 * Print list in order from head to tail.
	 */
	public void print() {
		Node<T> curr = head;
		
		while (curr != null) {
			System.out.print(curr.value);
			if (curr != tail) {
				System.out.print(" <-> ");
			}
			curr = curr.next;
		}
		
		System.out.print("\n");
	}

	public static void main(String[] args){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

		list.add("1");
		list.add("2");
		list.add("3");
		list.print();
		list.clear();
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.print();
		System.out.print(list.removeAt(6) + "\n");
		System.out.print(list.removeAt(3) + "\n");
		System.out.print(list.removeAt(0) + "\n");
		list.print();
		
		/**
		 * Output:
		 * 1 <-> 2 <-> 3
		 * 3 <-> 4 <-> 5 <-> 6 <-> 7
		 * false
		 * true
		 * true
		 * 4 <-> 5 <-> 7
		 */
	}
}
