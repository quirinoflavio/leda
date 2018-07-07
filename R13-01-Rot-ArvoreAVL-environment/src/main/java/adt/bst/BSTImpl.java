package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	protected int height(BSTNode<T> node) {
		int treeHeight;
		
		if (node == null || node.isEmpty()) {
			treeHeight = -1;
		} 
		else {
			int leftSide = height((BSTNode<T>) node.getLeft());
			int rightSide = height((BSTNode<T>) node.getRight());

			treeHeight = Math.max(leftSide, rightSide) + 1;
		}
		
		return treeHeight;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node;
		
		if (element == null) {
			node = (BSTNode<T>) new BSTNode.Builder<T>().build();
		} 
		else {
			node = search(root, element);
		}
		
		return node;
	}

	protected BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> result;

		if (node.isEmpty()) {
			result = (BSTNode<T>) new BSTNode.Builder<T>().build(); // NIL isolado da arvore.
		} 
		else if (node.getData().compareTo(element) == 0) {
			result = node;
		}
		else if (node.getData().compareTo(element) < 0) {
			result = search((BSTNode<T>) node.getRight(), element);
		} 
		else {
			result = search((BSTNode<T>) node.getLeft(), element);
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			insert(root, element);
		}
	}

	protected void insert(BSTNode<T> root, T element) {
		if (root.isEmpty()) {
			root.setData(element);
			root.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
			root.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
		} 
		else {
			if (root.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) root.getLeft(), element);
			} 
			else {
				insert((BSTNode<T>) root.getRight(), element);
			}
		}
	}
	
		

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> nodeMaximum;
		
		if (root.isEmpty()) {
			nodeMaximum = null;
		}
		else {
			nodeMaximum = maximum(root);
		}
		
		return nodeMaximum;
	}

	protected BSTNode<T> maximum(BSTNode<T> root) {
		while (!root.getRight().isEmpty()) {
			root = (BSTNode<T>) root.getRight();
		}
		
		return root;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> nodeMinimum;
		
		if (root.isEmpty()) {
			nodeMinimum = null;
		}
		else {
			nodeMinimum = minimum(root);
		}
		
		return nodeMinimum;
	}

	protected BSTNode<T> minimum(BSTNode<T> root) {
		while (!root.getLeft().isEmpty()) {
			root = (BSTNode<T>) root.getLeft();
		}
		
		return root;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		return sucessor(node);
	}

	protected BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> nodeSucessor;
		
		if (node.isEmpty()) {
			nodeSucessor = null;
		} 
		else if (!node.getRight().isEmpty()) {
			nodeSucessor = minimum((BSTNode<T>) node.getRight());
		} 
		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();

			while (parent != null && isRightChild(node, parent)) {
				node = parent;
				parent = (BSTNode<T>) parent.getParent();
			}
			
			nodeSucessor = parent;
		}
		
		return nodeSucessor;
	}

	protected boolean isRightChild(BSTNode<T> child, BSTNode<T> parent) {
		return !parent.getRight().isEmpty() && parent.getRight().getData().compareTo(child.getData()) == 0;
	}

	protected boolean isLeftChild(BSTNode<T> child, BSTNode<T> parent) {
		return !parent.getLeft().isEmpty() && parent.getLeft().getData().compareTo(child.getData()) == 0;
	}

	
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		return predecessor(node);
	}

	protected BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> nodePredecessor;
		
		if (node.isEmpty()) {
			nodePredecessor = null;
		} 
		else if (!node.getLeft().isEmpty()) {
			nodePredecessor = maximum((BSTNode<T>) node.getLeft());
		} 
		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();

			while (parent != null && isLeftChild(node, parent)) {
				node = parent;
				parent = (BSTNode<T>) parent.getParent();
			}

			nodePredecessor = parent;
		}

		return nodePredecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}

	protected void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
		}
		else {
			BSTNode<T> auxNode = null;
			if (!node.getRight().isEmpty()) {
				auxNode = minimum((BSTNode<T>) node.getRight());
			} 
			else {
				auxNode = maximum((BSTNode<T>) node.getLeft());
			}
			node.setData(auxNode.getData());
			remove(auxNode);
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];
		preOrderRecursive(array, root);
		return array;
	}

	private void preOrderRecursive(T[] array, BSTNode<T> root) {
		if (!root.isEmpty()) {
			addToArray(array, root.getData());
			preOrderRecursive(array, (BSTNode<T>) root.getLeft());
			preOrderRecursive(array, (BSTNode<T>) root.getRight());
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[size()];
		orderRecursive(array, root);
		return array;
	}

	private void orderRecursive(T[] array, BSTNode<T> root) {
		if (!root.isEmpty()) {
			orderRecursive(array, (BSTNode<T>) root.getLeft());
			addToArray(array, root.getData());
			orderRecursive(array, (BSTNode<T>) root.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[size()];
		postOrderRecursive(array, root);
		return array;
	}

	private void postOrderRecursive(T[] array, BSTNode<T> root) {
		if (!root.isEmpty()) {
			postOrderRecursive(array, (BSTNode<T>) root.getLeft());
			postOrderRecursive(array, (BSTNode<T>) root.getRight());
			addToArray(array, root.getData());
		}
	}
	

	/**
	 * Adiciona o data na primeira posicao nao nula do array
	 */
	protected void addToArray(T[] array, T data) {
		int i = 0;
		
		while (i < array.length) {
			if (array[i] == null) {
				array[i] = data;
				break;
			}
			
			i++;
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
