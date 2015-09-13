/**
 *	All values of the node has to be different for this Binary Search Tree implementation.
 *	If you run into trouble doing so, consider coming up with an attribute key containing UID.
 */
public class BinarySearchTree<T extends Comparable<T>> {
	public static class Node<T> {
		public T value;
		public int height;
		public Node<T> parent;
		public Node<T> left;
		public Node<T> right;

		public Node(T inputValue){
			this.value = inputValue;
			this.height = 0;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}
	
	public Node<T> root;
	public Node<T> smallest;
	public Node<T> largest;
	public int maxHeight;
	public int size;

	public BinarySearchTree() {
		root = null;
		maxHeight = 0;
		size = 0;
	}

	/**  
	 * Add input node to the appropriate location at binary tree.
	 * @param input value.
	 */
	public void insert(T inputValue) {
		Node<T> inputNode = new Node<T>(inputValue);
		size++;
		
		if (root == null) {
			root = inputNode;

		} else {
			Node<T> curr = traverseTo(inputValue);
			int compare = inputValue.compareTo(curr.value);

			if (compare < 0) {
				curr.left = inputNode;
				inputNode.parent = curr;
			} else if (compare > 0) {
				curr.right = inputNode;
				inputNode.parent = curr;
			}

			updateHeight(curr);
		}
	}

	/**  
	 * Get node with the specified value or the node just before that value.
	 * @note This is a PRIVATE function.
	 * @param input value.
	 * @return node if exists, null if doesn't exist.
	 */
	private Node<T> traverseTo(T inputValue) {
		Node<T> curr;

		if (root != null) {
			curr = root;
		} else {
			return null;
		}

		int compare;
		
		while (true) {
			compare = inputValue.compareTo(curr.value);

			if (compare < 0) {
				if (curr.left != null) {
					curr = curr.left;
				} else {
					break;
				}
			} else if (compare == 0) {
				break;

			} else if (compare > 0) {
				if (curr.right != null) {
					curr = curr.right;
				} else {
					break;
				}
			}
		}

		return curr;
	}

	/**  
	 * Update height of nodes up from input until root.
	 * @note This is a PRIVATE function.
	 * @param input node.
	 */
	private void updateHeight(Node<T> inputNode) {
		inputNode.height = Math.max((inputNode.left != null ? inputNode.left.height : -1), (inputNode.right != null ? inputNode.right.height : -1)) + 1;
	
		if (inputNode.parent != null) { 
			updateHeight(inputNode.parent);
		} else {
			maxHeight = inputNode.height;
		}
	}
	
	public Node<T> find(T inputValue) {
		return traverseTo(inputValue);
	}

	/**  
	 * Remove node with the specified value.
	 * @param input value.
	 */
	public void remove(T inputValue) {
		Node<T> curr = traverseTo(inputValue);

		if (curr.left != null && curr.right != null) {
			Node<T> successor = findSuccessor(curr);
			remove(successor.value);
			curr.value = successor.value;

			return;

		} else {
			int compare = curr.value.compareTo(curr.parent.value);
			Node<T> parent = curr.parent;

			if (compare < 0) {
				parent.left = curr.left != null ? curr.left : curr.right;

			} else if (compare == 0) {
				parent.left = curr.left != null ? curr.left : curr.right;

			} else if (compare > 0) {
				parent.right = curr.left != null ? curr.left : curr.right;
			} 
		}
	}
	
	/**  
	 * Get node with the specified value or the node just before that value.
	 * @note This is a PRIVATE function.
	 * @param input node (!= null).
	 * @return successor node of the input node.
	 */
	private Node<T> findSuccessor(Node<T> inputNode) {
		Node<T> curr = inputNode;

		if (inputNode.right != null) {
			curr = curr.right;

			while (curr.left != null) {
				curr = curr.left;
			}

		} else {
			while (curr.parent.left != curr) {
				curr = curr.parent;
			}

			curr = curr.parent;
		}

		return curr;
	}
	
	public void print() {
		if (root == null) {
			return;
		}
		
		System.out.print("Root\n");
		
		for (int i = 0; i < maxHeight + 1; i++) {
			printNodeAtHeight(root, i);
			printNewLine();
		}
	}

	/**  
	 * Print tree in order from root to leaf.
	 */
	private void printNodeAtHeight(Node<T> inputNode, int inputHeight) {
		if (inputNode == null) {
			printWhiteSpaces(1);
			return;
		}
 		
		if (inputHeight > 0) {
			printNodeAtHeight(inputNode.left, inputHeight - 1);
			printNodeAtHeight(inputNode.right, inputHeight - 1);
		} else if (inputHeight == 0) {
			printNode(inputNode);
		}
	}
	
	/**  
	 * Print white spaces.
	 * @note This is a PRIVATE function.
	 * @param input number.
	 */
	private void printWhiteSpaces(int inputNum) {
		for (int i = 0; i < inputNum; i++) {
			System.out.print(" ");
		}
	}

	/**  
	 * Print value of the node.
	 * @note This is a PRIVATE function.
	 * @param input node.
	 */
	private void printNode(Node<T> inputNode) {
		System.out.print(inputNode.value);
	}
	
	/**  
	 * Print new line.
	 * @note This is a PRIVATE function.
	 */
	private void printNewLine() {
		System.out.print("\n");
	}

	public static void main(String[] args){
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
	
		tree.insert("D");
		tree.insert("B");
		tree.insert("A");
		tree.insert("C");
		tree.insert("F");
		tree.insert("E");
		tree.insert("G");
		tree.print();
		tree.remove("D");
		tree.print();
		tree.remove("C");
		tree.print();
		System.out.print(tree.find("F").value + " ");
		System.out.print(tree.find("F").height + "\n");
		System.out.print(tree.find("G").value + " ");
		System.out.print(tree.find("G").height);
		
		/**
		 * Output:
		 * Root
		 * D
		 * BF
		 * ACEG
		 * Root
		 * E
		 * BF
		 * AC G
		 * Root
		 * E
		 * BF
		 * A  G
		 * F 1
		 * G 0
		 */
	}
}