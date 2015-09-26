/**
 *	All values of the node has to be different for this Binary Search Tree implementation.
 *	If you run into trouble doing so, consider coming up with an attribute key containing UID.
 */
public class AVLTree<T extends Comparable<T>> {
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
	public int maxHeight;
	public int size;

	public AVLTree() {
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
			checkBalanced(root);
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

	/**  
	 * Get height of the input node.
	 * @note This is a PRIVATE function.
	 * @param input node.
	 * @return height of node.
	 */
	private int getHeight(Node<T> inputNode) {
		if (inputNode == null) {
			return -1;
		} else {
			return inputNode.height;
		}
	}

	/**  
	 * Check if the tree is balanced, if not direct the function to rotate tree.
	 * @note This is a PRIVATE function.
	 * @param input node (starting node from which the check takes place).
	 */
	private void checkBalanced(Node<T> inputNode) {
		int leftHeight;
		int rightHeight;

		if (inputNode == null) {
			return;
		} else {
			leftHeight = getHeight(inputNode.left);
			rightHeight = getHeight(inputNode.right);

			if (leftHeight >= rightHeight + 2) {
				inputNode = inputNode.left;

				leftHeight = getHeight(inputNode.left);
				rightHeight = getHeight(inputNode.right);

				if (rightHeight >= leftHeight + 1) {
					rotateAntiClockwise(inputNode);
				}

				rotateClockWise(inputNode.parent);
			} else if (rightHeight >= leftHeight + 2) {
				inputNode = inputNode.right;

				leftHeight = getHeight(inputNode.left);
				rightHeight = getHeight(inputNode.right);

				if (leftHeight >= rightHeight + 1) {
					rotateClockWise(inputNode);
				}

				rotateAntiClockwise(inputNode.parent);
			}
		}

		checkBalanced(inputNode.left);
		checkBalanced(inputNode.right);
	}

	/**  
	 * Rotate the tree clockwise.
	 * @note This is a PRIVATE function.
	 * @param input node.
	 */
	private void rotateClockWise(Node<T> inputNode) {
		Node<T> inputLeftNode = inputNode.left;

		if (inputNode.parent != null) {
			inputLeftNode.parent = inputNode.parent;

			if (inputNode.parent.left == inputNode) {
				inputNode.parent.left = inputLeftNode;
			} else {
				inputNode.parent.right = inputLeftNode;
			}
		} else {
			inputLeftNode.parent = null;
			root = inputLeftNode;
		}

		if (inputLeftNode.right != null) {
			inputLeftNode.right.parent = inputNode;
			inputNode.left = inputLeftNode.right;
		} else {
			inputNode.left = null;
		}

		inputLeftNode.right = inputNode;
		inputNode.parent = inputLeftNode;

		updateHeight(inputNode);
	}

	/**  
	 * Rotate the tree anti-clockwise.
	 * @note This is a PRIVATE function.
	 * @param input node.
	 */
	private void rotateAntiClockwise(Node<T> inputNode) {
		Node<T> inputRightNode = inputNode.right;

		if (inputNode.parent != null) {
			inputRightNode.parent = inputNode.parent;

			if (inputNode.parent.right == inputNode) {
				inputNode.parent.right = inputRightNode;
			} else {
				inputNode.parent.left = inputRightNode;
			}
		} else {
			inputRightNode.parent = null;
			root = inputRightNode;
		}

		if (inputRightNode.left != null) {
			inputRightNode.left.parent = inputNode;
			inputNode.right = inputRightNode.left;
		} else {
			inputNode.right = null;
		}

		inputRightNode.left = inputNode;
		inputNode.parent = inputRightNode;

		updateHeight(inputNode);
	}

	/**  
	 * Get the node which corresponds to the input value.
	 * @param input value.
	 */
	public Node<T> getNode(T inputValue) {
		Node<T> node = traverseTo(inputValue);

		if (node.value == inputValue) {
			return node;
		} else {
			return null;
		}
	}

	/**  
	 * Remove node with the specified value.
	 * @param input value.
	 */
	public void remove(T inputValue) {
		Node<T> curr = getNode(inputValue);

		if (curr == null) {
			return;
		}

		if (curr.left != null && curr.right != null) {
			Node<T> successor = findSuccessor(curr);
			remove(successor.value);
			curr.value = successor.value;

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

		checkBalanced(root);
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
	
	/**  
	 * Print tree in order from root to leaf.
	 */
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
	 * Print all the nodes in the tree in that level.
	 * @note This is a PRIVATE function.
	 * @param input node and height of the tree that is being printed.
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
		AVLTree<String> tree = new AVLTree<String>();
	
		tree.insert("A");
		tree.insert("B");
		tree.insert("C");
		tree.insert("D");
		tree.insert("E");
		tree.insert("F");
		tree.insert("G");
		tree.print();
		tree.remove("D");
		tree.print();
		tree.remove("C");
		tree.print();
		System.out.print(tree.getNode("F").value + " ");
		System.out.print(tree.getNode("F").height + "\n");
		System.out.print(tree.getNode("G").value + " ");
		System.out.print(tree.getNode("G").height);
		
		/**
		 * Output:
		 * Root
		 * C
		 * BE
		 * A DF
		 *       G
		 * Root
		 * C
		 * BF
		 * A EG
		 * Root
		 * E
		 * BF
		 * A  G
		 * F 1
		 * G 0
		 */
	}
}