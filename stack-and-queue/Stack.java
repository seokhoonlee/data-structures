public class Stack<T> {
	public static class Node<T> {
		public T value;
		public Node<T> down;

		public Node(T value){
			this.value = value;
			this.down = null;
		}
	}

	public Node<T> top;
	public int height;

	public Stack() {
		top = null;
		height = 0;
	}

	/**  
	 * Add input node to top of the stack.
	 * @param input value.
	 */
	public void push(T inputValue) {
		Node<T> inputNode = new Node<T>(inputValue);
		
		if (top == null) {
			top = inputNode;
			
		} else {
			inputNode.down = top;
			top = inputNode;
		}
		
		height++;
	}

	/**  
	 * Clear all the contents in the list.
	 */
	public void clear() {
		top = null;
		height = 0;
	}

	/**  
	 * Remove node at the top and returns the value.
	 * @return value of the top node.
	 */
	public T pop() {
		if (top == null) {
			return null;
		}

		T temp = top.value;

		if (top.down != null) {
			top = top.down;
		} else {
			top = null;
		}

		height--;

		return temp;
	}

	/**  
	 * Print stack in order from top to bottom.
	 */
	public void print() {
		Node<T> curr = top;
		
		while (curr != null) {
			System.out.print(curr.value);
			if (curr.down != null) {
				System.out.print(" -> ");
			}
			curr = curr.down;
		}
		
		System.out.print("\n");
	}

	public static void main(String[] args){
		Stack<String> stack = new Stack<String>();

		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.print();
		stack.clear();
		stack.push("3");
		stack.push("4");
		stack.push("5");
		stack.push("6");
		stack.push("7");
		stack.print();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.print();

		/**
		 * Output:
		 * 3 -> 2 -> 1
		 * 7 -> 6 -> 5 -> 4 -> 3
		 * 4 -> 3
		 */
	}
}